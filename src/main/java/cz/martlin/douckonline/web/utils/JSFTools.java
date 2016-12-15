package cz.martlin.douckonline.web.utils;

import cz.martlin.douckonline.web.rest.LoginFilter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Some usefull JSF tools.
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
public class JSFTools {

    private static final Logger LOG = LoggerFactory.getLogger(JSFTools.class);
    private static final String MESSAGES_ID = null;//"the-global-messages";

    private static final String SAVED_INFO_TITLE = "Saved.";
    private static final String FAILED_ERROR_TITLE = "Failed!";

    private JSFTools() {
    }
//<editor-fold defaultstate="collapsed" desc="paths, redirects">

    /**
     * Returns current web path.
     *
     * @return
     */
    public static PathInfo getCurrentPath() {
	ExternalContext external = externalContext();
	Object request = external.getRequest();
	ServletRequest req = (ServletRequest) request;

	return LoginFilter.getCurrentPathInfo(req);
    }

    /**
     * Redirects to given path.
     *
     * @param path
     */
    public static void redirectTo(String path) {
	ExternalContext external = externalContext();
	try {
	    external.redirect(path);
	} catch (IOException ex) {
	    LOG.error("Cannot redirect to " + path);
	}
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="params">
    /**
     * REturns value of given GET param.
     *
     * @param name
     * @return
     */
    public static String getGETParam(String name) {
	return getRequestParam(name);
    }

    /**
     * Returns value of specified Request param.
     *
     * @param name
     * @return
     */
    public static String getRequestParam(String name) {
	ExternalContext context = externalContext();
	Map<String, String> params = context.getRequestParameterMap();
	String value = params.get(name);
	return value;
    }

//TODO getGETParamAsLong ?
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="more abstract messages reporting">
    /**
     * If success adds info with succText, else adds error with failText.
     *
     * @param success
     * @param succText
     * @param failText
     */
    public static void savedOrFailed(boolean success, String succText, String failText) {
	if (success) {
	    saved(succText);
	} else {
	    failed(failText);
	}
    }

    /**
     * Adds "cool, saved" message.
     *
     * @param text
     */
    public static void saved(String text) {
	//TODO
	info(SAVED_INFO_TITLE, text);
    }

    /**
     * Adds "fubar, it failed" message.
     *
     * @param text
     */
    public static void failed(String text) {
	//TODO
	error(FAILED_ERROR_TITLE, text);
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="messages">
    /**
     * Adds info.
     *
     * @param title
     * @param content
     */
    public static void info(String title, String content) {
	LOG.info("JSF INFO: " + title + ":" + content);
	addMessage(FacesMessage.SEVERITY_INFO, title, content);
    }

    /**
     * Adds error.
     *
     * @param title
     * @param content
     */
    public static void error(String title, String content) {
	LOG.info("JSF ERROR: " + title + ":" + content);
	addMessage(FacesMessage.SEVERITY_ERROR, title, content);
    }

    /**
     * Adds message.
     *
     * @param severity
     * @param title
     * @param content
     */
    public static void addMessage(FacesMessage.Severity severity, String title, String content) {
	FacesMessage message = new FacesMessage(severity, content, title);

	FacesContext.getCurrentInstance().addMessage(MESSAGES_ID, message);
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="JSF stuff">
    /**
     * Gets external context.
     *
     * @return
     */
    private static ExternalContext externalContext() {
	FacesContext context = FacesContext.getCurrentInstance();
	ExternalContext external = context.getExternalContext();

	return external;
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="some data conversions">
    /**
     * Converts date to calendar.
     *
     * @param date
     * @return
     */
    public static Calendar toCalendar(Date date) {
	Calendar calendar = Calendar.getInstance();

	calendar.setTime(date);

	return calendar;
    }

    /**
     * Converts calendar to date.
     *
     * @param calendar
     * @return
     */
    public static Date fromCalendar(Calendar calendar) {
	long milis = calendar.getTimeInMillis();
	Date date = new Date(milis);
	return date;
    }
//</editor-fold>
}
