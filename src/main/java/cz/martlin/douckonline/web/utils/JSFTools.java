package cz.martlin.douckonline.web.utils;

import cz.martlin.douckonline.business.model.managment.TeachingRequest;
import cz.martlin.douckonline.web.rest.LoginFilter;
import java.io.IOException;
import java.util.Map;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
public class JSFTools {
      private static final Logger LOG = LoggerFactory.getLogger(JSFTools.class);
    
    
      public static PathInfo getCurrentPath() {
	ExternalContext external = externalContext();
	Object request = external.getRequest();
	ServletRequest req = (ServletRequest) request; 
	
	return LoginFilter.getCurrentPathInfo(req);
      }

      
    public static void redirectTo(String path) {
	ExternalContext external = externalContext();
	try {
	    external.redirect(path);
	} catch (IOException ex) {
	    LOG.error("Cannot redirect to " + path);
	}
    }
    
    public static String getGETParam(String name) {
	//TODO
	return "TODOOOOO";
    }
    
    //TODO getGETParamAsLong ?
    
    public static void info(String title, String content) {
	//TODO
	LOG.info("JSF INFO: " + title + ":" + content);
    }
    
    
    public static void error(String title, String content) {
	//TODO
	LOG.info("JSF ERROR: " + title + ":" + content);
    }
    
    
    public static String getRequestParam(String name) {
	ExternalContext context = externalContext();
	Map<String, String> params = context.getRequestParameterMap();
	String value = params.get(name);
	return value;
    }
    
    private static ExternalContext externalContext() {
	FacesContext context = FacesContext.getCurrentInstance();
	ExternalContext external = context.getExternalContext();
	
	return external;
    }

}
