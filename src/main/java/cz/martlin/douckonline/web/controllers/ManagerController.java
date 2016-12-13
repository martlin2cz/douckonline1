package cz.martlin.douckonline.web.controllers;

import cz.martlin.douckonline.business.logic.Lectors;
import cz.martlin.douckonline.business.logic.Moneys;
import cz.martlin.douckonline.business.logic.Requests;
import cz.martlin.douckonline.business.logic.Students;
import cz.martlin.douckonline.business.logic.Subjects;
import cz.martlin.douckonline.business.logic.Teachings;
import cz.martlin.douckonline.business.model.lector.Lector;
import cz.martlin.douckonline.business.model.managment.Payment;
import cz.martlin.douckonline.business.model.managment.TeachingRequest;
import cz.martlin.douckonline.business.model.teaching.Lesson;
import cz.martlin.douckonline.business.model.teaching.Student;
import cz.martlin.douckonline.business.model.teaching.Subject;
import cz.martlin.douckonline.business.model.teaching.Teaching;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.validation.Payload;

/**
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
@RequestScoped
@Named("managerController")
public class ManagerController {
    
    private final Subjects subjects = new Subjects();
    private final Lectors lectors = new Lectors();
    private final Students students = new Students();
    
    private final Teachings teachings = new Teachings();
    private final Requests requests = new Requests();
    private final Moneys moneys = new Moneys();

    
    private List<TeachingRequest> currentRequests;
    private List<Teaching> currentTeachings;
    
    public ManagerController() {
    }
    
    @PostConstruct
    public void init() {
	currentRequests = requests.listAllPending();
	currentTeachings = teachings.listCurrentTeachings();
    }
    
    private Subject getSubject(String name) {
	return subjects.getSubject(name);
    }
    
    private Student getStudent(String loginName) {
	return students.getStudent(loginName);
    }
    
    
    private Lector getLector(String loginName) {
	return lectors.getLector(loginName);
    }
    
    
    //<editor-fold defaultstate="collapsed" desc="public api or something like that">
    
    
    public List<TeachingRequest> getCurrentRequests() {
	return currentRequests;
    }
    
    public List<Teaching> getCurrentTeachings() {
	return currentTeachings;
    }
    
    
    
//</editor-fold>
    
    public List<Lector> getEnglishLectors() {
	return lectors.listLectorsOfSubject(getSubject("english"));
    }
        
    public List<Lector> getMathLectors() {
	return lectors.listLectorsOfSubject(getSubject("math"));
    }
    
    
    public List<Payment> getPaymentsOfEveMilly() {
	return moneys.listPayments(getStudent("MomMilly"), -1);
    }
    
    public List<Payment> getPaymentsOfMikeNerd() {
	return moneys.listPayments(getStudent("MikeNerd"), -1);
    }
    
    
    public List<Teaching> getTeachingsOfPeterGeek() {
	return teachings.listTeachingsOfLector(getLector("PeterGeek"));
    }
    
    public List<Teaching> getTeachingsOfJaneBach() {
	return teachings.listTeachingsOfLector(getLector("JaneBach"));
    }
    
    
    public List<Teaching> getTeachingsOfEveMilly() {
	return teachings.listTeachingsOfStudent(getStudent("MomMilly"));
    }
    
    public List<Teaching> getTeachingsOfMikeNerd() {
	return teachings.listTeachingsOfStudent(getStudent("MikeNerd"));
    }
    
    public List<Teaching> getTeachingsOfGeekAndNerd() {
	return teachings.getTeachingsOf(getLector("PeterGeek"), getStudent("MikeNerd"), getSubject("math"));
    }
    
    
    public List<Lesson> getLessonsOfGeek() {
	return teachings.getLessonsOf(getLector("PeterGeek"), -1);
    }
    
    public List<Lesson> getLessonsOfBach() {
	return teachings.getLessonsOf(getLector("JaneBach"), -1);
    }
    
    
    public List<Lesson> getLessonsOfNerd() {
	return teachings.getLessonsOf(getStudent("MikeNerd"), -1);
    }
    
    public List<Lesson> getLessonsOfLazy() {
	return teachings.getLessonsOf(getStudent("MaryLazy"), -1);
    }
    
    
    
    
    
    //TODO ?
    

}
