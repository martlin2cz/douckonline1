/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.martlin.douckonline.business.data;

import cz.martlin.douckonline.business.model.lector.Lector;
import cz.martlin.douckonline.business.model.teaching.Student;
import cz.martlin.douckonline.business.tools.DbAccessor;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
public class Students {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final DbAccessor db = new DbAccessor();

    public Students() {
    }
  
    
    
    public List<Student> listAllStudents() {
	LOG.debug("Loading students");
	List<Student> students = db.listAll(Student.class);
	return students;
    }
    
    
    public boolean addStudent(Student student) {
	LOG.debug("Adding student");
	return db.insert(student);
    }

    public Student getStudent(String loginName) {
	return db.getById(Student.class, loginName);
    }
}
