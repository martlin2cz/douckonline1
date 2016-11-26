package cz.martlin.douckonline.business.test;

import cz.martlin.douckonline.business.data.Lectors;
import cz.martlin.douckonline.business.data.Managers;
import cz.martlin.douckonline.business.data.Moneys;
import cz.martlin.douckonline.business.data.Students;
import cz.martlin.douckonline.business.data.Subjects;
import cz.martlin.douckonline.business.data.Teachings;
import cz.martlin.douckonline.business.model.lector.Certificate;
import cz.martlin.douckonline.business.model.lector.Education;
import cz.martlin.douckonline.business.model.lector.Lector;
import cz.martlin.douckonline.business.model.managment.Manager;
import cz.martlin.douckonline.business.model.lector.Practice;
import cz.martlin.douckonline.business.model.teaching.Student;
import cz.martlin.douckonline.business.model.lector.SubjTeachingSpec;
import cz.martlin.douckonline.business.model.lector.Suitability;
import cz.martlin.douckonline.business.model.teaching.Lesson;
import cz.martlin.douckonline.business.model.teaching.Level;
import cz.martlin.douckonline.business.model.teaching.Subject;
import cz.martlin.douckonline.business.model.teaching.Teaching;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
public class TestDataCreator {
    private static final Subjects subjects = new Subjects();
    private static final Lectors lectors = new Lectors();
    private static final Students students = new Students();
    private static final Managers managers = new Managers();
    private static final Teachings teachings = new Teachings();
    private static final Moneys moneys = new Moneys();
    
    private final String LECTOR_JOHNSMART_LOGIN = "johnsmart";
    private final String STUDENT_KARLLAZY_LOGIN = "karllazy";
    
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

    private final SubjTeachingSpec SUBJ_TEACH_1 = new SubjTeachingSpec(0, ENGLISH, Level.ALL, Suitability.PRIMARY_SUBJECT, "conversation", 150);

    public void create() {
	createSubjects();
	createLectorsMeta();
	createLectors();
	createStudents();
	createManagers();

	createTeaching1();
	createLessons1();
	createPayments1();
    }

    private void createSubjects() {

	subjects.addSubject(ENGLISH);
	subjects.addSubject(MATH);
    }

    private void createLectorsMeta() {
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

	lectors.addSubjTeachSpec(SUBJ_TEACH_1);
    }

    private void createLectors() {
	String fullName = "John Smart";
	String titleBeforeName = "Mrs. et Mgr.";
	String titleAfterName = "PhD.";
	String phone = "756 225 330";
	String email = "johy@smarty.com";
	String bankAccountNumber = "420-44556686/0110";
	List<SubjTeachingSpec> subjects = list(SUBJ_TEACH_1);
	List<Certificate> certificates = list(CERTIFICATE_1);
	List<Education> educations = list(EDUCATION_1, EDUCATION_2);
	List<Practice> experiences = list(PRACTISE_1);
	String loginName = LECTOR_JOHNSMART_LOGIN;
	String passwordHash = "ThisIsAPasswordHash";
	String passwordSalt = "this-is-very-salt-password-salt";
	Calendar registeredAt = daysAgo(7);
	Calendar lastLoginAt = daysAgo(1);
	Lector johny = new Lector(fullName, titleBeforeName, titleAfterName, phone, email, bankAccountNumber, subjects, certificates, educations, experiences, loginName, passwordHash, passwordSalt, registeredAt, lastLoginAt);

	lectors.addLector(johny);

    }

    private void createStudents() {
	String loginName = STUDENT_KARLLAZY_LOGIN;
	String passwordHash = "ThisIsAnotherPasswordHash";
	String passwordSalt = "this-is-very-sweet-password-salt";
	Calendar registeredAt = daysAgo(4);
	Calendar lastLoginAt = daysAgo(2);
	String registerName = "Karl's mom";
	String studentName = "Karl Lazy";
	String phone = "756 225 330";
	String email = "johy@smarty.com";
	String bankAccountNumber = "420-44556686/0110";

	Student karl = new Student(registerName, studentName, phone, email, bankAccountNumber, loginName, passwordHash, passwordSalt, registeredAt, lastLoginAt);
	students.addStudent(karl);
    }

    private void createManagers() {

	String loginName = "billbosyy";
	String passwordHash = "ThisIsYetAnotherPasswordHash";
	String passwordSalt = "this-is-very-hot-password-salt";
	Calendar registeredAt = daysAgo(10);
	Calendar lastLoginAt = daysAgo(0);
	String fullName = "Bill Bossy";

	Manager bill = new Manager(fullName, loginName, passwordHash, passwordSalt, registeredAt, lastLoginAt);
	managers.addManager(bill);
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

	calendar.add(Calendar.DAY_OF_MONTH, -days);

	return calendar;
    }
    
    
    private Calendar time(int hours, int minutes) {
	Calendar calendar = Calendar.getInstance();

	calendar.set(Calendar.HOUR_OF_DAY, hours + 1);
	calendar.set(Calendar.MINUTE, minutes);
	calendar.set(Calendar.SECOND, 0);
	
	return calendar;
    }

    private void createTeaching1() {

	Lector lector = lectors.getLector(LECTOR_JOHNSMART_LOGIN);
	Student student = students.getStudent(STUDENT_KARLLAZY_LOGIN);
	Subject subject = ENGLISH;
	Level level = Level.ONLY_BASIC;
	int cost = 145;
	
	teachings.startTeaching(lector, student, subject, level, cost);

    }

    private void createLessons1() {
	Lector lector = lectors.getLector(LECTOR_JOHNSMART_LOGIN);
	Student student = students.getStudent(STUDENT_KARLLAZY_LOGIN);
		
	Subject subject = ENGLISH;
	Calendar date = daysAgo(1);
	Calendar duration = time(1, 30);
	String description = "Past perfect";

	teachings.addLesson(lector, student, subject, date, duration, description);
    }

    private void createPayments1() {
	Student student = students.getStudent(STUDENT_KARLLAZY_LOGIN);
	
	Calendar date = daysAgo(1);
	int amount = 500;
	int discount = 5;
	moneys.addPayment(student, date, amount, discount);
    }


}
