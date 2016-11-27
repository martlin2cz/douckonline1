package cz.martlin.douckonline.business.logic;

import cz.martlin.douckonline.business.model.lector.Certificate;
import cz.martlin.douckonline.business.model.lector.Education;
import cz.martlin.douckonline.business.model.lector.Lector;
import cz.martlin.douckonline.business.model.lector.Practice;
import cz.martlin.douckonline.business.model.lector.SubjTeachingSpec;
import cz.martlin.douckonline.business.model.teaching.Subject;
import cz.martlin.douckonline.business.tools.DbAccessor;
import java.util.Calendar;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
public class Lectors {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final DbAccessor db = new DbAccessor();
    
    public Lectors() {
    }
    
//<editor-fold defaultstate="collapsed" desc="load lector(s)">
    /**
     * Lists all lectors.
     * @return
     */
    public List<Lector> listAllLectors() {
	LOG.trace("Loading lectors");
	List<Lector> lectors = db.listAll(Lector.class);
	return lectors;
    }
    
    /**
     * List lectors teaching specified subject.
     * @param subject
     * @return 
     */
    public List<Lector> listLectorsOfSubject(Subject subject) {
	LOG.trace("Loading lectors of subject");
	Class<?> froms[] = {Lector.class, SubjTeachingSpec.class, Subject.class};
	String attrs[] = { "subjTeachingSpec.subject" };
	String vars[] = { "subject" };
	Object values[] = { subject };
	
	List<Lector> lectors = db.listBySimpleCond(Lector.class, froms, attrs, vars, values);
	return lectors;
    }
    
    public Lector getLector(String loginName) {
	LOG.trace("Loading lector by loginName");
	return db.getById(Lector.class, loginName);
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="Add, modify lector">
    
    /**
     * Adds specified lector into database.
     * @param lector
     * @return
     */
    public boolean addLector(Lector lector, String password) {
	LOG.trace("Adding lector");
	
	final Users users = new Users();
	return users.registerUser(lector, password);
    }
    
    /**
     * Saves modified lector into database.
     * @param lector
     * @return
     */
    public boolean updateLector(Lector lector) {
	LOG.trace("Updating lector");
	return db.update(lector);
    }
    
    /**
     * Sets lector as inactive until now.
     * @param lector
     * @return
     */
    public boolean makeLectorInactive(Lector lector) {
	LOG.trace("Making lector inactive");
	Calendar when = Calendar.getInstance();
	lector.setEndedAt(when);
	return db.update(lector);
    }
    
    /**
     * Makes lector active again.
     * @param lector
     * @return
     */
    public boolean makeLectorActiveAgain(Lector lector) {
	LOG.trace("Making lector active again");
	lector.setEndedAt(null);
	return db.update(lector);
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="certificates, educations, practices, subjects specs">
    /**
     * To specified lector adds given certificate.
     * @param lector
     * @param certificate
     * @return
     */
    public boolean addCertificate(Lector lector, Certificate certificate) {
	LOG.trace("Adding certificate");
	
	lector.getCertificates().add(certificate);
	return db.insert(certificate);
    }
    
    /**
     * To specified lector removes given certificate.
     * @param lector
     * @param certificate
     * @return 
     */
    public boolean removeCertificate(Lector lector, Certificate certificate) {
	LOG.trace("Removing certificate");
	
	lector.getCertificates().remove(certificate);
	return db.remove(certificate);
    }
    
    /**
     * To specified lector adds given education.
     * @param lector
     * @param education
     * @return
     */
    public boolean addEducation(Lector lector, Education education) {
	LOG.trace("Adding education");
	
	lector.getEducations().add(education);
	return db.insert(education);
    }
    
    /**
     * To specified lector removes given education.
     * @param lector
     * @param education
     * @return 
     */
    public boolean removeEducation(Lector lector, Education education) {
	LOG.trace("Removing education");
	
	lector.getEducations().remove(education);
	return db.remove(education);
    }
    
    /**
     * To specified lector adds given parctice.
     * @param lector
     * @param practice
     * @return
     */
    public boolean addPractise(Lector lector, Practice practice) {
	LOG.trace("Adding practice");
	
	lector.getPractices().add(practice);
	return db.insert(practice);
    }
    
    /**
     * To specified lector removes given parctice.
     * @param lector
     * @param practice
     * @return 
     */
     public boolean removePractise(Lector lector, Practice practice) {
	LOG.trace("Removing practice");
	
	lector.getPractices().remove(practice);
	return db.remove(practice);
    }
    
    /**
     * To specified lector adds given subject teaching specification.
     * @param lector
     * @param spec
     * @return
     */
    public boolean addSubjTeachSpec(Lector lector, SubjTeachingSpec spec) {
	LOG.trace("Adding subject teaching specification");
	
	lector.getSubjects().add(spec);
	return db.insert(spec);
    }
    
    /**
     * To specified lector removes given subject teaching specification.
     * @param lector
     * @param spec
     * @return 
     */
    public boolean removeSubjTeachSpec(Lector lector, SubjTeachingSpec spec) {
	LOG.trace("Removing subject teaching specification");
	
	lector.getSubjects().remove(spec);
	return db.remove(spec);
    }
//</editor-fold>
    
 
}

