package cz.martlin.douckonline.web.controllers;

import cz.martlin.douckonline.business.logic.Teachings;
import cz.martlin.douckonline.business.model.lector.Lector;
import cz.martlin.douckonline.business.model.teaching.Lesson;
import cz.martlin.douckonline.business.model.teaching.Teaching;
import cz.martlin.douckonline.web.rest.LoginSession;
import cz.martlin.douckonline.web.utils.JSFTools;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * @see LessonsController or something like that
 * @author m@rtlin <martlin@seznam.cz>
 */
@ViewScoped
@ManagedBean(name = "lessonsController")
//@RequestScoped
//@Named("lessonsController")
public class LessonsController {

    private final Teachings TEACHINGS = new Teachings();
    @Inject private LoginSession login;
    
    private Lesson lessonToOpen;
    private List<Teaching> teachings;

    public LessonsController() {
	lessonToOpen = new Lesson();
    }
    
    @PostConstruct
    public void init() {
	Lector lector = login.getLoggedLector();
	teachings = TEACHINGS.listTeachingsOfLector(lector);
    }

//<editor-fold defaultstate="collapsed" desc="getters and setters">
    public Lesson getLessonToOpen() {
	return lessonToOpen;
    }

    public void setLessonToOpen(Lesson lessonToOpen) {
	this.lessonToOpen = lessonToOpen;
    }

    public List<Teaching> getTeachings() {
	return teachings;
    }

    public void setTeachings(List<Teaching> teachings) {
	this.teachings = teachings;
    }
    
    
 //</editor-fold>

//<editor-fold defaultstate="collapsed" desc="action methods">
    public void openNewLesson(Teaching teaching) {
	//
	lessonToOpen.setTeaching(teaching);
    }

    public void openLesson(Lesson lesson) {
	lessonToOpen = lesson;
    }

    public void saveLesson(Lesson lesson) {
	boolean success;
	if (lesson.isPersisted()) {
	    JSFTools.failed("unsupported operation");
	    throw new UnsupportedOperationException("update lesson");
	} else {
	    success = TEACHINGS.addLesson(lesson);
	}
	JSFTools.savedOrFailed(success, "Lesson saved", "Could not save lesson");
    }
//</editor-fold>
}
