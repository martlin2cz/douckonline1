package cz.martlin.douckonline.business.logic;

import cz.martlin.douckonline.business.model.lector.Lector;
import cz.martlin.douckonline.business.model.teaching.Subject;
import cz.martlin.douckonline.business.tools.DbLoading;
import cz.martlin.douckonline.business.tools.DbModifying;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
public class Subjects {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final DbLoading dbl = DbLoading.get();
    private final DbModifying dbm = DbModifying.get();
    
    public Subjects() {
    }
    
    
    
//<editor-fold defaultstate="collapsed" desc="listing">
    
    public List<Subject> listAllSubjects() {
	LOG.trace("Loading all subjects");
	
	List<Subject> subjects = dbl.listAll(Subject.class);
	return subjects;
    }
    
    public Subject getSubject(String name) {
	LOG.trace("Getting subject");
	
	Subject subject = dbl.getById(Subject.class, name);
	return subject;
    }
    
    //TODO list numbers of students/teachings/lessons of each subject?
    
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="add, remove subject">
    
    public boolean addSubject(Subject subject) {
	LOG.trace("Adding subject");
	return dbm.insertSingle(subject);
    }
    
    
    public boolean removeSubject(Subject subject) {
	LOG.trace("Removing subject");
	return dbm.removeSingle(subject);
    }
    
//</editor-fold>

    
}
