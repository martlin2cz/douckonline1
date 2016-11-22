package cz.martlin.douckonline.business.data;

import cz.martlin.douckonline.business.model.Lector;
import cz.martlin.douckonline.business.model.Subject;
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

    private final DbAccessor db = new DbAccessor();
    
    public Subjects() {
    }
    
    
    
    
    public List<Subject> listAllSubjects() {
	LOG.debug("Loading subjects");
	List<Subject> subjects = db.listAll(Subject.class);
	return subjects;
    }
    
    
    public boolean addSubject(Subject subject) {
	LOG.debug("Adding subject " + subject);
	return db.insert(subject);
    }
    
}
