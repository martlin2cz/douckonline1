package cz.martlin.douckonline.business.data;

import cz.martlin.douckonline.business.model.lector.Lector;
import cz.martlin.douckonline.business.model.managment.Payment;
import cz.martlin.douckonline.business.model.teaching.Lesson;
import cz.martlin.douckonline.business.model.teaching.Level;
import cz.martlin.douckonline.business.model.teaching.Student;
import cz.martlin.douckonline.business.model.teaching.Subject;
import cz.martlin.douckonline.business.model.teaching.Teaching;
import cz.martlin.douckonline.business.tools.DbAccessor;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
public class Teachings {
    private final Logger LOG = LoggerFactory.getLogger(getClass());
    private static final String STARTED_TEACHING_DESCRIPTION = "Just started";
    
    private final DbAccessor db = new DbAccessor();
    
    public Teachings() {
    }
    
    
    public List<Teaching> listTeachingsOfLector(Lector lector) {
	// TODO 
	throw new UnsupportedOperationException("teachings of lector");
    }
    
    
    public List<Teaching> listTeachingsOfStudent(Student student) {
	// TODO
	throw new UnsupportedOperationException("teachings of student");
    }
    
    
    public List<Teaching> listCurrentTeachings() {
	// TODO
	throw new UnsupportedOperationException("current teachings");
    }
    
    
    public boolean startTeaching(Lector lector, Student student, Subject subject, Level level, int cost) {
	LOG.debug(lector + " starts to teach " + student + " the " + subject + " with level " + level);
	
	Teaching teaching = createTeaching(lector, student, subject, level, cost);
	return db.insert(teaching);
    }

    private Teaching createTeaching(Lector lector, Student student, Subject subject, Level level, int cost) {
	List<Lesson> lessons = new ArrayList(0);
	Calendar startedAt = Calendar.getInstance();
	String statusDescription = STARTED_TEACHING_DESCRIPTION;
	
	Teaching teaching = new Teaching(0, lector, student, subject, level, cost, lessons, startedAt, null, statusDescription);
	return teaching;
    }

    public int getBallanceOfStudent(Student student) {
	return 42;  //TODO
	
    }

    public List<Lesson> getLessonsOf(Student student, Integer daysAgo) {
	Class<?>[] froms = {Lesson.class, Student.class};
	String[] attrs = {"student"};
	String[] vars = {"student"};
	Object[] values = { student };
	//TODO daysAgo
	//TODO WHERE lesson.teaching.student, not item.student (!)
	return db.listBySimpleCond(Lesson.class, froms, attrs, vars, values);
    }

    public List<Payment> getPaymentsOfStudent(Student student, Integer daysAgo) {
	Class<?>[] froms = {Payment.class, Student.class};
	String[] attrs = {"student"};
	String[] vars = {"student"};
	Object[] values = { student };
	//TODO daysAgo
	return db.listBySimpleCond(Payment.class, froms, attrs, vars, values);
    }

    public List<Teaching> getTeachingsOf(Lector lector, Student student, Subject subject) {
	Class<?>[] froms = {Teaching.class};
	String[] attrs = {"teaching.student", "teaching.lector", "teaching.subject"};
	String[] vars = {"student", "lector", "subject"};
	Object[] values = { student, lector, subject };
	//TODO if subject == null ...
	return db.listBySimpleCond(Teaching.class, froms, attrs, vars, values);
    
    }

    public boolean addLesson(Lector lector, Student student, Subject subject, Calendar date, Calendar duration, String description) {
	Teaching teaching = getTeachingsOf(lector, student, subject).get(0);	//TODO if (.size() != 1)
	Lesson lesson = new Lesson(0, teaching, date, duration, description);
	return db.insert(lesson);
    }
}
