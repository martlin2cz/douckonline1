/*
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.martlin.douckonline.web.controllers;

import cz.martlin.douckonline.business.model.lector.Lector;
import cz.martlin.douckonline.business.model.teaching.Student;
import cz.martlin.douckonline.web.utils.LoginSession;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
@RequestScoped
@Named("studentsProfileController")
public class StudentProfileController {
    @Inject private LoginSession session;

    public StudentProfileController() {
    }
    
    public Student getLoggedStudent() {
	return session.getLoggedStudent();
    }
    
}
