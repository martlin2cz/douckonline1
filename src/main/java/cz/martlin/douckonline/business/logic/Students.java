package cz.martlin.douckonline.business.logic;

import cz.martlin.douckonline.business.model.lector.Lector;
import cz.martlin.douckonline.business.model.teaching.Student;
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
public class Students {
    private final Logger LOG = LoggerFactory.getLogger(getClass());
    
    private final DbLoading dbl = DbLoading.get();
    private final DbModifying dbm = DbModifying.get();

    public Students() {
    }
  
//<editor-fold defaultstate="collapsed" desc="listing of students">
    
    /**
     * Loads all students.
     * @return 
     */
    public List<Student> listAllStudents() {
	LOG.trace("Loading all students");
	
	List<Student> students = dbl.listAll(Student.class);
	return students;
    }
    
    /**
     * Loads all curretly active students (have some active teaching).
     * @return 
     */
    public List<Student> listCurrentlyActiveStudents() {
	LOG.trace("Loading all currently active students");
	//TODO FIXME
	LOG.warn("not implemented, returning all students instead");
	
	List<Student> students = dbl.listAll(Student.class);
	return students;
    }
    
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="loading, adding, updating of student">
    
    /**
     * Gets student by its loginName.
     * @param loginName
     * @return 
     */
    public Student getStudent(String loginName) {
	LOG.trace("Loading student by loginName");
	return dbl.getById(Student.class, loginName);
    }
    
    /**
     * Adds given student.
     * @param student
     * @return 
     */
    public boolean addStudent(Student student) {
	LOG.trace("Adding student");
	LOG.warn("generating of student's password not yet implemented");
	final Users users = new Users();
	
	String password = "student-password";	//TODO FIXME generate student' passwd
	
	return users.registerUser(student, password);
    }
    
    /**
     * Updates student.
     * @param student
     * @return 
     */
    public boolean updateStudent(Student student) {
	LOG.trace("Updating student");
	return dbm.updateSingle(student);
    }
    
//</editor-fold>

}
