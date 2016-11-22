package cz.martlin.douckonline.business.test;

import cz.martlin.douckonline.business.data.Lectors;
import cz.martlin.douckonline.business.data.Subjects;
import cz.martlin.douckonline.business.model.Certificate;
import cz.martlin.douckonline.business.model.Education;
import cz.martlin.douckonline.business.model.Lector;
import cz.martlin.douckonline.business.model.Practice;
import cz.martlin.douckonline.business.model.Subject;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
public class TestDataCreator {
    private final Subject ENGLISH = new Subject("english");
    private final Subject MATH = new Subject("math");
    
    private final Certificate CERTIFICATE_1 = new Certificate(0, "FCE", ENGLISH, "C1");
    private final Certificate CERTIFICATE_2 = new Certificate(0, "ASA", MATH, "poor");
    private final Certificate CERTIFICATE_3 = new Certificate(0, "CCC", ENGLISH, "A1");
    
    private final Education EDUCATION_1 = new Education(0, "Palacký university", "Metamathematics", "doc.");
    private final Education EDUCATION_2 = new Education(0, "Charles University, Prague", "Just - nothing", "prof.");
    private final Education EDUCATION_3 = new Education(0, "King's Colleage", "Computer Science", "PhD.");
    private final Education EDUCATION_4 = new Education(0, "My high school", "engeneering", "grad");
    
    private final Practice PRACTISE_1 = new Practice(0, "counter", "Local shop");
    private final Practice PRACTISE_2 = new Practice(0, "doing nothing", "ahome");
    private final Practice PRACTISE_3 = new Practice(0, "Erasmus", "U.S.A");
    
    
    public void create() {
	createSubjects();
	createLectorsMeta();
	createLectors();
    }

    
    private void createSubjects() {
	Subjects subjects = new Subjects();
	subjects.addSubject(ENGLISH);
	subjects.addSubject(MATH);
    }
    
    
    private void createLectorsMeta() {
	Lectors lectors = new Lectors();
	lectors.addCertificate(CERTIFICATE_1);
	lectors.addCertificate(CERTIFICATE_2);
	lectors.addCertificate(CERTIFICATE_3);
	
	lectors.addEducation(EDUCATION_1);
	lectors.addEducation(EDUCATION_2);
	lectors.addEducation(EDUCATION_3);
	lectors.addEducation(EDUCATION_4);
	
	lectors.addPractise(PRACTISE_1);
	lectors.addPractise(PRACTISE_2);
	lectors.addPractise(PRACTISE_3);
    }
    

    private void createLectors() {
	Lectors lectors = new Lectors();
	
	String fullName = "John Smart";
	String titleBeforeName = "Mrs. et Mgr.";
	String titleAfterName = "PhD.";
	List<Subject> subjects = list(ENGLISH);
	List<Certificate> certificates = list(CERTIFICATE_1);
	List<Education> educations = list(EDUCATION_1, EDUCATION_2);
	List<Practice> experiences = list(PRACTISE_1); 
	String loginName = "johnsmart";
	String passwordHash = "ThisIsAPasswordHash";
	String passwordSalt = "this-is-very-salt-password-salt";
	Calendar registeredAt = daysAgo(7);
	Calendar lastLoginAt = daysAgo(1);
	Lector johny = new Lector(fullName, titleBeforeName, titleAfterName, subjects, certificates, educations, experiences, loginName, passwordHash, passwordSalt, registeredAt, lastLoginAt);
	
	lectors.addLector(johny);
	
	/*
	lectors.addLector(new Lector("John"));
	lectors.addLector(new Lector("Jimmy"));
	lectors.addLector(new Lector("Bill"));
	lectors.addLector(new Lector("John"));
	*/
	
    }
    
    public static <T> List<T> list(T item) {
	List<T> list = new ArrayList<>();
	
	list.add(item);
	
	return list;
    }
    
    
    public static <T> List<T> list(T item1, T item2) {
	List<T> list = new ArrayList<>();
	
	list.add(item1);
	list.add(item2);
	
	return list;
    }
    
    public static Calendar daysAgo(int days) {
	Calendar calendar = Calendar.getInstance();
	
	calendar.add(Calendar.DAY_OF_MONTH, - days);
	
	return calendar;
    }


}

