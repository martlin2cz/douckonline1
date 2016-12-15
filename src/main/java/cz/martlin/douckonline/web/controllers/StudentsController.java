package cz.martlin.douckonline.web.controllers;

import cz.martlin.douckonline.business.logic.Students;
import cz.martlin.douckonline.business.model.teaching.Student;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
@RequestScoped
@Named("studentsController")
public class StudentsController {

    private final Students STUDENTS = new Students();

    private List<Student> students;

    public StudentsController() {
    }

    @PostConstruct
    public void init() {
	students = STUDENTS.listAllStudents();
    }

//<editor-fold defaultstate="collapsed" desc="getters and setters">
    public List<Student> getStudents() {
	return students;
    }
    
    public void setStudents(List<Student> students) {
	this.students = students;
    }
//</editor-fold>
    
    
}
