
package cz.martlin.douckonline.web.controllers;

import cz.martlin.douckonline.business.logic.Teachings;
import cz.martlin.douckonline.business.model.teaching.Lesson;
import cz.martlin.douckonline.business.model.teaching.Teaching;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
@RequestScoped
@Named("teachingsController")
public class TeachingsController {
    
    private Teaching currentTeaching;
    private Lesson currentLesson;
    
    public TeachingsController() {
	currentTeaching = null;
    }
//<editor-fold defaultstate="collapsed" desc="getters and setters">
    
    public Teaching getCurrentTeaching() {
	return currentTeaching;
    }
    
    
    public Lesson getCurrentLesson() {
	return currentLesson;
    }
    
    
//</editor-fold>
    
    
//<editor-fold defaultstate="collapsed" desc="action methods">
   
//</editor-fold>

    
    
}
