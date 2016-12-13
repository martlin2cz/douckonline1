package cz.martlin.douckonline.web.controllers;

import cz.martlin.douckonline.business.logic.Lectors;
import cz.martlin.douckonline.business.logic.Users;
import cz.martlin.douckonline.business.model.lector.Lector;
import cz.martlin.douckonline.business.model.base.User;
import cz.martlin.douckonline.web.rest.LoginFilter;
import cz.martlin.douckonline.web.rest.LoginSession;
import cz.martlin.douckonline.web.utils.JSFTools;
import cz.martlin.douckonline.web.utils.PathInfo;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
@RequestScoped
@Named("logingInController")
public class LogingInController {
    private final Users users = new Users();
    
    
    @Inject LoginSession session;

    
    private String username;
    private String password;
    
    public LogingInController() {
    }
    
//<editor-fold defaultstate="collapsed" desc="getters and setters">
    
    public String getUsername() {
	return username;
    }
    
    public void setUsername(String username) {
	this.username = username;
    }
    
    public String getPassword() {
	return password;
    }
    
    public void setPassword(String password) {
	this.password = password;
    }
    
    
    
    public boolean isLoggedIn() {
	return session.isLoggedIn();
    }
//</editor-fold>
    

//<editor-fold defaultstate="collapsed" desc="action methods">
    
    public void logIn() {
	User user = users.findUser(username);
	
	session.logInAs(user);
	
	checkAndRedirect();
    }
    
    
    
    public void logOut() {
	session.logOut();
	
	checkAndRedirect();
    }
    
    private void checkAndRedirect() {
	PathInfo info = JSFTools.getCurrentPath();
	String path = LoginFilter.checkLoginAndGetRedirect(info, session);
	
	if (path != null) {
	    JSFTools.redirectTo(path);
	}
    }
//</editor-fold>
}
