package cz.martlin.douckonline.web.controllers;

import cz.martlin.douckonline.business.logic.Subjects;
import cz.martlin.douckonline.business.model.teaching.Subject;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;

/**
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
@RequestScoped
@Named("subjectsController")
public class SubjectsController {
    
    private final Subjects SUBJECTS = new Subjects();
    
    private List<Subject> allSubjects;
    

    public SubjectsController() {
    }
    
    @PostConstruct
    public void init() {
	 allSubjects = SUBJECTS.listAllSubjects();
    }
    
    //<editor-fold defaultstate="collapsed" desc="getters and setters">
    
    public List<Subject> getAllSubjects() {
	return allSubjects;
    }
    
    
//</editor-fold>
    
    
    //TODO add/remove/update? subject
    
}
