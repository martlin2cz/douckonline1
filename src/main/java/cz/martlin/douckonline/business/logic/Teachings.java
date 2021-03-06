package cz.martlin.douckonline.business.logic;

import cz.martlin.douckonline.business.model.lector.Lector;
import cz.martlin.douckonline.business.model.managment.Payment;
import cz.martlin.douckonline.business.model.teaching.Lesson;
import cz.martlin.douckonline.business.model.teaching.Level;
import cz.martlin.douckonline.business.model.teaching.Student;
import cz.martlin.douckonline.business.model.teaching.Subject;
import cz.martlin.douckonline.business.model.teaching.Teaching;
import cz.martlin.douckonline.business.tools.DbLoading;
import cz.martlin.douckonline.business.tools.DbModifying;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implements logic about the teachings (teachings and lessons).
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
public class Teachings {

    private final Logger LOG = LoggerFactory.getLogger(getClass());
    private static final String STARTED_TEACHING_DESCRIPTION = "Just started";

    private final DbLoading dbl = DbLoading.get();
    private final DbModifying dbm = DbModifying.get();

    public Teachings() {
    }

//<editor-fold defaultstate="collapsed" desc="listing of teaching">
    /**
     * Finds teaching by id.
     *
     * @param id
     * @return
     */
    public Teaching getTeachingById(long id) {
	LOG.trace("Finding teaching by id");

	return dbl.getById(Teaching.class, id);
    }

    /**
     * Lists all teachings of given lector.
     *
     * @param lector
     * @return
     */
    public List<Teaching> listTeachingsOfLector(Lector lector) {
	LOG.trace("Listing teachings of lector");

	List<Teaching> teachings = dbl.listByCond(Teaching.class, false, //
		new Class<?>[]{Lector.class}, //
		new String[]{"teaching.lector"}, //
		new String[]{"lector"}, //
		new Object[]{lector});

	return teachings;
    }

    /**
     * Lists all techings of given student.
     *
     * @param student
     * @return
     */
    public List<Teaching> listTeachingsOfStudent(Student student) {
	LOG.trace("Listing teachings of student");

	List<Teaching> teachings = dbl.listByCond(Teaching.class, false, // 
		new Class<?>[]{Student.class}, //
		new String[]{"teaching.student"}, //
		new String[]{"student"}, //
		new Object[]{student});

	return teachings;
    }

    /**
     * Lists teaching(s) of given lector AND student AND subject.
     *
     * @param lector
     * @param student
     * @param subject
     * @return
     */
    public List<Teaching> getTeachingsOf(Lector lector, Student student, Subject subject) {
	LOG.trace("Listing teachins of lector, student and subject");

	List<Teaching> teachings = dbl.listByCond(Teaching.class, true, //
		new Class<?>[]{Lector.class, Student.class, Subject.class}, //
		new String[]{"teaching.lector", "teaching.student", "teaching.subject"}, //
		new String[]{"lector", "student", "subject"}, //
		new Object[]{lector, student, subject});

	return teachings;

    }

    /**
     * Lists all currently active teachings.
     *
     * @return
     */
    public List<Teaching> listCurrentTeachings() {
	LOG.trace("Listing all current teachings");

	//TODO FIXME
	LOG.warn("not yet implemented, returns all teachings instead");

	return dbl.listAll(Teaching.class);
    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="start, finish, update teaching">
    /**
     * Starts teaching of specified params.
     *
     * @param lector
     * @param student
     * @param subject
     * @param level
     * @param cost
     * @return
     */
    public Teaching startTeaching(Lector lector, Student student, Subject subject, Level level, int cost) {
	LOG.trace("Starting of teaching");

	Teaching teaching = createTeaching(lector, student, subject, level, cost);
	boolean success = dbm.insertSingle(teaching);
	if (success) {
	    return teaching;
	} else {
	    return null;
	}
    }

    /**
     * Finishes given teaching with specified reason message.
     *
     * @param teaching
     * @param reason
     * @return
     */
    public boolean finishTeaching(Teaching teaching, String reason) {
	LOG.trace("Finising of teaching");

	String statusDescription = reason;
	Calendar when = Calendar.getInstance();

	teaching.setStatusDescription(statusDescription);
	teaching.setEndedAt(when);

	return dbm.update(teaching);
    }

    /**
     * Updates given teaching (statusDescription, level, ...).
     *
     * @param teaching
     * @return
     */
    public boolean updateTeaching(Teaching teaching) {
	LOG.trace("Updating teaching");

	return dbm.update(teaching);
    }

    /**
     * Creates instance of Teaching class of given params.
     *
     * @param lector
     * @param student
     * @param subject
     * @param level
     * @param cost
     * @return
     */
    private Teaching createTeaching(Lector lector, Student student, Subject subject, Level level, int cost) {
	List<Lesson> lessons = new ArrayList(0);
	Calendar startedAt = Calendar.getInstance();
	Calendar stoppedAt = null;
	String statusDescription = STARTED_TEACHING_DESCRIPTION;

	Teaching teaching = new Teaching(lector, student, subject, level, cost, lessons, startedAt, stoppedAt, statusDescription);
	return teaching;
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="list, add, remove lessons">
    /**
     * Loads lessons of specified student and (if provided) only for last amount
     * of days.
     *
     * @param student
     * @param daysAgo
     * @return
     */
    public List<Lesson> getLessonsOf(Student student, Integer daysAgo) {
	LOG.trace("Listing lessons of student and daysAgo");

	List<Lesson> teachings = dbl.listByCond(Lesson.class, false, //
		new Class<?>[]{Teaching.class, Student.class}, //
		new String[]{"lesson.teaching.student"}, //
		new String[]{"student"}, //
		new Object[]{student});

	return teachings;
    }

    /**
     * Loads lessons of specified lector and (if provided) only for last amount
     * of days.
     *
     * @param lector
     * @param daysAgo
     * @return
     */
    public List<Lesson> getLessonsOf(Lector lector, Integer daysAgo) {
	LOG.trace("Listing lessons of lector and daysAgo");

	List<Lesson> teachings = dbl.listByCond(Lesson.class, false, //
		new Class<?>[]{Teaching.class, Lector.class}, //
		new String[]{"lesson.teaching.lector"}, //
		new String[]{"lector"}, //
		new Object[]{lector});

	return teachings;
    }

    /**
     * Adds lesson to database.
     *
     * @param lesson
     * @return
     */
    public boolean addLesson(Lesson lesson) {
	LOG.trace("Adding lesson");

	Calendar addedAt = Calendar.getInstance();
	lesson.setAddedAt(addedAt);

	Teaching teaching = lesson.getTeaching();
	teaching.getLessons().add(lesson);

	boolean success = dbm.insertSingle(lesson);
	return success;
    }

    /**
     * Removes given lesson from database.
     *
     * @param lesson
     * @return
     */
    public boolean removeLesson(Lesson lesson) {
	LOG.trace("Removing lesson");

	Teaching teaching = lesson.getTeaching();
	teaching.getLessons().remove(lesson);

	return dbm.removeSingle(lesson);
    }

    /**
     * Updates given lesson in database.
     *
     * @param lesson
     * @return
     */
    public boolean updateLesson(Lesson lesson) {
	LOG.trace("Updating lesson");

	LOG.warn("will loose data like AddedAt ... (restore from db and copy it into?)? Or not?");

	return dbm.updateSingle(lesson);
    }
//</editor-fold>

}
