package cz.martlin.douckonline.web.controllers;

import cz.martlin.douckonline.business.logic.Lectors;
import cz.martlin.douckonline.business.logic.Subjects;
import cz.martlin.douckonline.business.model.lector.Lector;
import cz.martlin.douckonline.business.model.teaching.Subject;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
@RequestScoped
@Named("lectorsController")
public class LectorsController {

    private final Lectors lectors = new Lectors();
    private final Subjects subjects = new Subjects();

    private List<Lector> currentLectors;
    private List<Subject> allSubjects;

    private Subject selectedSubject;
    private String newLectorName;

    public LectorsController() {
    }

    
    private void loadLectors() {
	if (selectedSubject != null) {
	    currentLectors = lectors.listLectorsOfSubject(selectedSubject);
	} else {
	    currentLectors = lectors.listAllLectors();
	}
    }

    
    @PostConstruct
    public void init() {
	loadLectors();	
	allSubjects = subjects.listAllSubjects();
    }
    
//<editor-fold defaultstate="collapsed" desc="getters and setters">
    
    public List<Lector> getLectors() {
	return currentLectors;
    }
    
    public List<Subject> getAllSubjects() {
	return allSubjects;
    }
    
    public Subject getSelectedSubject() {
	return selectedSubject;
    }
    
    public void setSelectedSubject(Subject selectedSubject) {
	this.selectedSubject = selectedSubject;
    }
    
    public String getNewLectorName() {
	return newLectorName;
    }
    
    public void setNewLectorName(String newLectorName) {
	this.newLectorName = newLectorName;
    }
//</editor-fold>
    
    public void filter() {
	loadLectors();
    }
    

}
