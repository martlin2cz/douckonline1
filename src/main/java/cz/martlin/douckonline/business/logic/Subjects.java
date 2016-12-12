package cz.martlin.douckonline.business.logic;

import cz.martlin.douckonline.business.model.lector.Lector;
import cz.martlin.douckonline.business.model.teaching.Subject;
import cz.martlin.douckonline.business.tools.DbAccessor;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
public class Subjects {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final DbAccessor db = DbAccessor.get();
    
    public Subjects() {
    }
    
    
    
//<editor-fold defaultstate="collapsed" desc="listing">
    
    public List<Subject> listAllSubjects() {
	LOG.trace("Loading all subjects");
	
	List<Subject> subjects = db.listAll(Subject.class);
	return subjects;
    }
    
    public Subject getSubject(String name) {
	LOG.trace("Getting subject");
	
	Subject subject = db.getById(Subject.class, name);
	return subject;
    }
    
    //TODO list numbers of students/teachings/lessons of each subject?
    
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="add, remove subject">
    
    public boolean addSubject(Subject subject) {
	LOG.trace("Adding subject");
	return db.insert(subject);
    }
    
    
    public boolean removeSubject(Subject subject) {
	LOG.trace("Removing subject");
	return db.remove(subject);
    }
    
//</editor-fold>

    
}
