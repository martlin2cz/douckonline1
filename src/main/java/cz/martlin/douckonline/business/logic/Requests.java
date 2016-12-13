package cz.martlin.douckonline.business.logic;

import cz.martlin.douckonline.business.model.lector.Lector;
import cz.martlin.douckonline.business.model.lector.SubjTeachingSpec;
import cz.martlin.douckonline.business.model.managment.RequestReaction;
import cz.martlin.douckonline.business.model.managment.RequestReactionStatus;
import cz.martlin.douckonline.business.model.managment.TeachingRequest;
import cz.martlin.douckonline.business.model.managment.TeachingRequestStatus;
import cz.martlin.douckonline.business.model.teaching.Level;
import cz.martlin.douckonline.business.model.teaching.Student;
import cz.martlin.douckonline.business.model.teaching.Subject;
import cz.martlin.douckonline.business.model.teaching.Teaching;
import cz.martlin.douckonline.business.tools.DbLoading;
import cz.martlin.douckonline.business.tools.DbModifying;
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
    
    private final DbLoading dbl = DbLoading.get();
    private final DbModifying dbm = DbModifying.get();
    
    private final Teachings teachings = new Teachings();
    private final Lectors lectors = new Lectors();
    private final Students students = new Students();

    public Requests() {
    }
//<editor-fold defaultstate="collapsed" desc="finding (by id)">
    
    public TeachingRequest getRequestById(long id) {
	LOG.trace("Getting teaching requst by id");
	
	return dbl.getById(TeachingRequest.class, id);
    }
    
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="listing of requests">

    public List<TeachingRequest> listAllPending() {
	LOG.trace("Listing all peding requests");

	return dbl.listByCond(TeachingRequest.class,
		new String[]{"status"},
		new Object[]{TeachingRequestStatus.WAITING_FOR_REACTIONS});
    }

    public List<TeachingRequest> listPendingOfSubject(Subject subject) {
	LOG.trace("Listing all peding requests of subject");

	return dbl.listByCond(TeachingRequest.class,
		new String[]{"subject", "status"},
		new Object[]{subject, TeachingRequestStatus.WAITING_FOR_REACTIONS});
    }

    public List<TeachingRequest> listPendingForLector(Lector lector) {
	LOG.trace("Listing all pending requests for lector");

	return dbl.listByCond(TeachingRequest.class, false,
		new Class<?>[]{Subject.class, SubjTeachingSpec.class, Lector.class},
		new String[]{"lector", "teachingRequest.status"},
		new String[]{"lector", "status"},
		new Object[]{lector, TeachingRequestStatus.WAITING_FOR_REACTIONS});
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="reactions">
    
    public boolean react(TeachingRequest request, Lector lector, RequestReactionStatus status, String description) {
	LOG.trace("Reacting to request");

	dbm.startBulk();
	RequestReaction reaction = createReaction(lector, status, description);
	
	addReaction(request, reaction);
	
	dbm.finishBulk();
	return dbm.isSuccessfull();
    }
    
    public boolean addReaction(TeachingRequest request, RequestReaction reaction) {
	LOG.trace("Adding reaction to request");
	
	
	request.getReactions().add(reaction);
	reaction.setRequest(request);
	
	return dbm.insertSingle(reaction);
    }
    
     private RequestReaction createReaction(Lector lector, RequestReactionStatus status, String description) {
	 
	Calendar when = Calendar.getInstance();
	
	return new RequestReaction(null, lector, status, when, description);
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="creating requests and converting to teaching">
    public boolean addRequest(TeachingRequest request) {
	LOG.trace("Adding request");

	Calendar addedAt = Calendar.getInstance();
	request.setAddedAt(addedAt);
	
	TeachingRequestStatus status = TeachingRequestStatus.WAITING_FOR_REACTIONS;
	request.setStatus(status);
	
	return dbm.insertSingle(request);
    }
    
    
    private boolean updateRequestStatus(TeachingRequest request) {
	LOG.trace("Updating request's status");

	return dbm.updateSingle(request);
	
    }
    
    public Teaching reactionToTeaching(RequestReaction reaction) {
	LOG.trace("Making teaching from lector's reaction");
	
	dbm.startBulk();
	
	TeachingRequest request = reaction.getRequest();
	request.setStatus(TeachingRequestStatus.TEACHING_RUNNING);
	updateRequestStatus(request);
	
	Lector lector = reaction.getLector();
	String description = "Okay, let's go";	//TODO description
	int cost = 42;	//TODO 42
	Teaching t = requestToTeaching(request, lector, description, cost);
	
	dbm.finishBulk();
	return t;
    }

    public Teaching requestToTeaching(TeachingRequest request, Lector lector, String description, int cost) {
	LOG.trace("Making teaching from request");
	
	dbm.startBulk();

	Student student = requestToStudent(request);
	Subject subject = request.getSubject();
	Level level = request.getLevel();
	//TODO description
	
	Teaching teaching = teachings.startTeaching(lector, student, subject, level, cost);
	
	request.setStatus(TeachingRequestStatus.TEACHING_RUNNING);
	updateRequestStatus(request);
	
	dbm.finishBulk();
	
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
