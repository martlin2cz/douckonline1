package cz.martlin.douckonline.web.rest;

import cz.martlin.douckonline.web.utils.PathInfo;
import cz.martlin.douckonline.business.model.lector.Lector;
import cz.martlin.douckonline.business.model.managment.Manager;
import cz.martlin.douckonline.business.model.base.User;
import cz.martlin.douckonline.business.model.teaching.Student;
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
    
    private static final String XHTML_FILE_PATTERN = "^(.+)\\.xhtml$";
    private static final String RESOURCE_DIR = "javax.faces.resource";
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
    public void destroy() {
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	PathInfo info = getCurrentPathInfo(request);
	
	if (info.getFoldersCount() <= 1) {
	    String newPath = checkLoginAndGetRedirect(info, login);
	    LOG.debug("Having site path " + info + " with login " + login + ", will redirect to " + newPath);
	    
	    if (newPath != null) {
		redirectTo(newPath, response);
		return;
	    }
	}	
	
	chain.doFilter(request, response);
    }

    public static PathInfo getCurrentPathInfo(ServletRequest request) {
	String path = getPath(request);
	String root = getContextRoot(request);
	PathInfo info = PathInfo.create(path, root, XHTML_FILE_PATTERN);
	info.popFolder(); //context root
	
	return info;
    }
    

    private static String getPath(ServletRequest request) {
	HttpServletRequest req = (HttpServletRequest) request;
	return req.getRequestURI();
    }
    
    
    private static String getContextRoot(ServletRequest request) {
	HttpServletRequest req = (HttpServletRequest) request;
	return req.getContextPath();
    }
    
    private static void redirectTo(String newPath, ServletResponse response) throws IOException {
	HttpServletResponse res = (HttpServletResponse) response;
	res.sendRedirect(newPath);
    }  

    public static String checkLoginAndGetRedirect(PathInfo info, LoginSession login) {
	String currentFolder = info.getFoldersCount() == 0 ? null : info.getFolder(0);
	String expectedFolder = getRequiredFolder(login);
	if (RESOURCE_DIR.equals(currentFolder)) {
	    return null;
	}
	
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

    public static String getRequiredFolder(LoginSession login) {
	User loggedUser = login.getLoggedUser();
	if (loggedUser != null) {
	    return FOLDERS.get(loggedUser.getClass());
	} else {
	    return null;
	}
    }
}
