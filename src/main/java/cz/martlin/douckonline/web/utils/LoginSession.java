package cz.martlin.douckonline.web.utils;

import cz.martlin.douckonline.business.model.Lector;
import cz.martlin.douckonline.business.model.User;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
@SessionScoped
@Named("loginSession")
public class LoginSession implements Serializable {
    
    private User loggedUser;

    public LoginSession() {
    }

    public User getLoggedUser() {
	return loggedUser;
    }

    public boolean isLoggedIn() {
	return loggedUser != null;
    }
    
    
    public void logInAs(User loggedUser) {
	this.loggedUser = loggedUser;
    }

    public Lector getLoggedLector() {
	//TODO FIXME what if not?
	return (Lector) loggedUser;
    }
    
    
    
    
}
