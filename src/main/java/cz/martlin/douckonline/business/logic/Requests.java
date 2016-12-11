package cz.martlin.douckonline.business.logic;

import cz.martlin.douckonline.business.model.lector.Lector;
import cz.martlin.douckonline.business.model.lector.SubjTeachingSpec;
import cz.martlin.douckonline.business.model.managment.RequestReaction;
import cz.martlin.douckonline.business.model.managment.TeachingRequest;
import cz.martlin.douckonline.business.model.managment.TeachingRequestStatus;
import cz.martlin.douckonline.business.model.teaching.Level;
import cz.martlin.douckonline.business.model.teaching.Student;
import cz.martlin.douckonline.business.model.teaching.Subject;
import cz.martlin.douckonline.business.model.teaching.Teaching;
import cz.martlin.douckonline.business.tools.DbAccessor;
import java.util.Calendar;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
public class Requests {

    private final Logger LOG = LoggerFactory.getLogger(getClass());
    private final DbAccessor db = new DbAccessor();
    private final Teachings teachings = new Teachings();
    private final Lectors lectors = new Lectors();
    private final Students students = new Students();

    public Requests() {
    }
//<editor-fold defaultstate="collapsed" desc="listing of requests">

    public List<TeachingRequest> listAllPending() {
	LOG.trace("Listing all peding requests");

	return db.listByCond(TeachingRequest.class,
		new String[]{"status"},
		new Object[]{TeachingRequestStatus.WAITING_FOR_REACTIONS});
    }

    public List<TeachingRequest> listPendingOfSubject(Subject subject) {
	LOG.trace("Listing all peding requests of subject");

	return db.listByCond(TeachingRequest.class,
		new String[]{"subject", "status"},
		new Object[]{subject, TeachingRequestStatus.WAITING_FOR_REACTIONS});
    }

    public List<TeachingRequest> listPendingForLector(Lector lector) {
	LOG.trace("Listing all pending requests for lector");

	return db.listByCond(TeachingRequest.class, false,
		new Class<?>[]{Subject.class, SubjTeachingSpec.class, Lector.class},
		new String[]{"lector", "teachingRequest.status"},
		new String[]{"lector", "status"},
		new Object[]{lector, TeachingRequestStatus.WAITING_FOR_REACTIONS});
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="reactions">
    
    public boolean react(TeachingRequest request, RequestReaction reaction) {
	LOG.trace("Adding reaction to request");
	
	Calendar when = Calendar.getInstance();
	reaction.setWhen(when);
	
	request.getReactions().add(reaction);
	reaction.setRequest(request);
	
	
	return db.insert(reaction);
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="creating requests and converting to teaching">
    public boolean addRequest(TeachingRequest request) {
	LOG.trace("Adding request");

	Calendar addedAt = Calendar.getInstance();
	request.setAddedAt(addedAt);
	
	return db.insert(request);
    }

    public Teaching requestToTeaching(TeachingRequest request, Lector lector) {
	LOG.trace("Making teaching from request");

	Student student = requestToStudent(request);
	Subject subject = request.getSubject();
	Level level = request.getLevel();
	int cost = lectors.getSubjOfLector(lector, subject).getCost();

	Teaching teaching = teachings.startTeaching(lector, student, subject, level, cost);
	return teaching;

    }

    private Student requestToStudent(TeachingRequest request) {
	LOG.trace("Making teaching from request");

	String registerName = request.getRegisterName();
	String studentName = request.getStudentName();
	String email = request.getEmail();
	String phone = request.getPhone();
	String bankAccountNumber = null;
	String adress = null;
	String loginName = null;
	String passwordHash = null;
	String passwordSalt = null;
	Calendar registeredAt = null;
	Calendar lastLoginAt = null;

	Student student = new Student(registerName, studentName, email, phone, bankAccountNumber, adress, loginName, passwordHash, passwordSalt, registeredAt, lastLoginAt);

	boolean success = students.addStudent(student);
	if (success) {
	    return student;
	} else {
	    return null;
	}
    }
//</editor-fold>
}
