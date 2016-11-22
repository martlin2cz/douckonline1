package cz.martlin.douckonline.web.controllers;

import cz.martlin.douckonline.business.data.Lectors;
import cz.martlin.douckonline.business.data.Users;
import cz.martlin.douckonline.business.model.Lector;
import cz.martlin.douckonline.business.model.User;
import cz.martlin.douckonline.web.utils.LoginSession;
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
    }
}
