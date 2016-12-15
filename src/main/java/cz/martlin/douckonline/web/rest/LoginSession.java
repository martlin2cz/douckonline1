package cz.martlin.douckonline.web.rest;

import cz.martlin.douckonline.business.model.lector.Lector;
import cz.martlin.douckonline.business.model.managment.Manager;
import cz.martlin.douckonline.business.model.teaching.Student;
import cz.martlin.douckonline.business.model.base.User;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 * Session scoped bean holding logged user and performs various encapusilaton.
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
@SessionScoped
@Named("loginSession")
public class LoginSession implements Serializable {

    private User loggedUser;

    public LoginSession() {
    }

//<editor-fold defaultstate="collapsed" desc="getters">
    public User getLoggedUser() {
	return loggedUser;
    }

    public boolean isLoggedIn() {
	return loggedUser != null;
    }

    public boolean isLoggedAsStudent() {
	return isLoggedIn() && loggedUser instanceof Student;
    }

    public boolean isLoggedAsLector() {
	return isLoggedIn() && loggedUser instanceof Lector;
    }

    public boolean isLoggedAsManager() {
	return isLoggedIn() && loggedUser instanceof Manager;
    }

    public Lector getLoggedLector() {
	if (isLoggedAsLector()) {
	    return (Lector) loggedUser;
	} else {
	    return null;
	}
    }

    public Student getLoggedStudent() {
	if (isLoggedAsStudent()) {
	    return (Student) loggedUser;
	} else {
	    return null;
	}
    }

    public Manager getLoggedManager() {
	if (isLoggedAsManager()) {
	    return (Manager) loggedUser;
	} else {
	    return null;
	}
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="methods">
    public void logInAs(User loggedUser) {
	this.loggedUser = loggedUser;
    }

    public void logOut() {
	this.loggedUser = null;
    }

//</editor-fold>
    
    @Override
    public String toString() {
	return "LoginSession{" + loggedUser + "}";
    }

}
