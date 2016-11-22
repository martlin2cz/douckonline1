/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.martlin.douckonline.web.controllers;

import cz.martlin.douckonline.business.model.Lector;
import cz.martlin.douckonline.web.utils.LoginSession;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
@RequestScoped
@Named("lectorsProfileController")
public class LectorProfileController {
    @Inject private LoginSession session;

    public LectorProfileController() {
    }
    
    public Lector getLoggedLector() {
	return session.getLoggedLector();
    }
    
}
