/*
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.martlin.douckonline.web.controllers;

import cz.martlin.douckonline.business.logic.Moneys;
import cz.martlin.douckonline.business.logic.Teachings;
import cz.martlin.douckonline.business.model.lector.Lector;
import cz.martlin.douckonline.business.model.managment.Payment;
import cz.martlin.douckonline.business.model.teaching.Lesson;
import cz.martlin.douckonline.business.model.teaching.Student;
import cz.martlin.douckonline.web.rest.LoginSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
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
    private static final int SHOW_LESSONS_FOR_DAYS = 30;
    private static final int SHOW_PAYMENTS_FOR_DAYS = 60;
    
    private final Moneys moneys = new Moneys();
    private final Teachings teachings = new Teachings();
    @Inject private LoginSession session;

    public StudentProfileController() {
    }
    
    public Student getLoggedStudent() {
	return session.getLoggedStudent();
    }
    
    public BigDecimal getBallance() {
	return moneys.getBallanceOfStudent(getLoggedStudent());
    }
    
    public BigDecimal getIncomes() {
	return moneys.getPaymentsOfStudent(getLoggedStudent());
    }
    
    public BigDecimal getOutcomes() {
	return moneys.getOutcomesOfStudent(getLoggedStudent()).negate();
    }
    
    public List<Lesson> getActualLessons() {
	return teachings.getLessonsOf(getLoggedStudent(), SHOW_LESSONS_FOR_DAYS);
    }
    
    public List<Payment> getActualPayments() {
	return moneys.listPayments(getLoggedStudent(), SHOW_PAYMENTS_FOR_DAYS);
    }
    
    
}

