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
    
    private User selectedUser;
    
    @Inject LoginSession session;

    public LogingInController() {
    }

    public User getSelectedUser() {
	return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
	this.selectedUser = selectedUser;
    }
    
    public boolean isLoggedIn() {
	return session.isLoggedIn();
    }
    
    public User getLoggedUser() {
	return session.getLoggedUser();
    }
    
    
    public List<User> getUsers() {
	return users.listAllUsers();
    }
    
    public void logInAs() {
	session.logInAs(selectedUser);
	
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
}
