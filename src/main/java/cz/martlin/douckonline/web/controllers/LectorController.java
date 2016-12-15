package cz.martlin.douckonline.web.controllers;

import cz.martlin.douckonline.business.logic.Lectors;
import cz.martlin.douckonline.business.model.lector.Lector;
import cz.martlin.douckonline.web.utils.JSFTools;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
@RequestScoped
@Named("lectorController")
public class LectorController {

    public static final String LOGIN_NAME_GET_PARAM_NAME = "login-name";

    private final Lectors LECTORS = new Lectors();

    private Lector lectorToOpen;

    public LectorController() {
    }

    @PostConstruct
    public void init() {
	String login = JSFTools.getGETParam(LOGIN_NAME_GET_PARAM_NAME);
	if (login != null) {
	    Lector lector = LECTORS.getLector(login);
	    if (lector != null) {
		lectorToOpen = lector;
		return;
	    } else {
		JSFTools.error("Not found", "Lector " + login + " not found");
	    }
	}

	lectorToOpen = new Lector();
    }
    //<editor-fold defaultstate="collapsed" desc="getters and setters">

    public Lector getLectorToOpen() {
	return lectorToOpen;
    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="action methods">
    //TODO update lector, add/remove subjs, certs, edus and practices
//</editor-fold>
}
