package cz.martlin.douckonline.web.rest;

import cz.martlin.douckonline.business.model.lector.Lector;
import cz.martlin.douckonline.business.model.managment.Manager;
import cz.martlin.douckonline.business.model.managment.User;
import cz.martlin.douckonline.business.model.teaching.Student;
import cz.martlin.douckonline.web.utils.LoginSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
@WebFilter("/*")
public class LoginFilter implements Filter {
    private final Logger LOG = LoggerFactory.getLogger(getClass());
   
    private static final Map<Class<?>, String> FOLDERS = initFolders();
    
    @Inject private LoginSession login;
    
    public LoginFilter() {
    }

    private static Map<Class<?>, String> initFolders() {
	Map<Class<?>, String> folders = new HashMap<>();
	
	//folders.put(User.class, "");
	folders.put(Student.class, "student");
	folders.put(Manager.class, "manager");
	folders.put(Lector.class, "lector");
	
	return folders;
    }
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	String path = getPath(request);
	String root = getContextRoot(request);
	
	PathInfo info = PathInfo.create(path, root, XHTML_FILE_PATTERN);
	info.popFolder(); //context root
	
	if (info.getFoldersCount() <= 1) {
	    String newInfo = checkLoginAndGetRedirect(info);
	    LOG.debug("Having site path " + info + " with login " + login + ", will redirect to " + newInfo);
	    if (newInfo != null) {
		redirectTo(newInfo, response);
	    }
	    
	    /*
	    LOG.debug("Having site path " + info + " with login " + login);
	    boolean modified = modifyPath(info);
	    if (modified) {
		//info.pushFolder(root);
		LOG.debug("Will redirect to " + info);
		
		String newPath = info.toPath(false);
		redirectTo(newPath, response);
	    } 
	    */
	}	
	
	chain.doFilter(request, response);
    }
    protected static final String XHTML_FILE_PATTERN = "^(.+)\\.xhtml$";

    @Override
    public void destroy() {
    }

    private static String getPath(ServletRequest request) {
	HttpServletRequest req = (HttpServletRequest) request;
	return req.getRequestURI();
    }
    
    
    private static String getContextRoot(ServletRequest request) {
	HttpServletRequest req = (HttpServletRequest) request;
	return req.getContextPath();
    }
    
    private boolean modifyPath(PathInfo info) {
	User loggedUser = login.getLoggedUser();
	String expectedFolder;
	
	if (loggedUser != null)  {
	    expectedFolder = FOLDERS.get(loggedUser.getClass());
	} else {
	    expectedFolder = null;//FOLDERS.get(User.class);
	}
	
	if (info.getFoldersCount() == 0 && expectedFolder == null) {
	    return false;
	}
	if (info.getFoldersCount() == 1 && expectedFolder == null) {
	    info.changeFolder(0, "..");
	    return true;
	}
	if (info.getFoldersCount() == 0 && expectedFolder != null) {
	    info.pushFolder(expectedFolder);
	    return true;
	}
	if (info.getFoldersCount() == 1 && expectedFolder != null) {
	    String realFolder = info.getFolder(0);
	    if (!realFolder.equals(expectedFolder)) {
		info.changeFolder(0, expectedFolder);
		return true;
	    } else {
		return false;
	    }
	}
	return false;
    }

    private void redirectTo(String newPath, ServletResponse response) throws IOException {
	HttpServletResponse res = (HttpServletResponse) response;
	res.sendRedirect(newPath);
    }  

    private String checkLoginAndGetRedirect(PathInfo info) {
	String currentFolder = info.getFoldersCount() == 0 ? null : info.getFolder(0);
	String expectedFolder = getRequiredFolder(login);
	
	if (currentFolder == null && expectedFolder != null) {
	    return expectedFolder;
	}
	if (currentFolder != null && expectedFolder == null) {
	    return "..";
	}
	if (currentFolder != null && expectedFolder != null) {
	    if (currentFolder.equals(expectedFolder)) {
		return null;
	    } else {
		return "../" + expectedFolder;
	    }
	}
	if (currentFolder == null && expectedFolder == null) {
	    return null;
	}
	
	return null;
    }

    private String getRequiredFolder(LoginSession login) {
	User loggedUser = login.getLoggedUser();
	if (loggedUser != null) {
	    return FOLDERS.get(loggedUser.getClass());
	} else {
	    return null;
	}
    }
}
