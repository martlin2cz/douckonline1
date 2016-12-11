package cz.martlin.douckonline.business.test;

import cz.martlin.douckonline.business.logic.Lectors;
import cz.martlin.douckonline.business.logic.Managers;
import cz.martlin.douckonline.business.logic.Moneys;
import cz.martlin.douckonline.business.logic.Requests;
import cz.martlin.douckonline.business.logic.Students;
import cz.martlin.douckonline.business.logic.Subjects;
import cz.martlin.douckonline.business.logic.Teachings;
import cz.martlin.douckonline.business.model.lector.Certificate;
import cz.martlin.douckonline.business.model.lector.Education;
import cz.martlin.douckonline.business.model.lector.Lector;
import cz.martlin.douckonline.business.model.managment.Manager;
import cz.martlin.douckonline.business.model.lector.Practice;
import cz.martlin.douckonline.business.model.teaching.Student;
import cz.martlin.douckonline.business.model.lector.SubjTeachingSpec;
import cz.martlin.douckonline.business.model.lector.Suitability;
import cz.martlin.douckonline.business.model.managment.RequestReaction;
import cz.martlin.douckonline.business.model.managment.RequestReactionStatus;
import cz.martlin.douckonline.business.model.managment.TeachingRequest;
import cz.martlin.douckonline.business.model.managment.TeachingRequestStatus;
import cz.martlin.douckonline.business.model.teaching.Level;
import cz.martlin.douckonline.business.model.teaching.Subject;
import cz.martlin.douckonline.business.model.teaching.SubjectCategory;
import cz.martlin.douckonline.business.model.teaching.Teaching;
import cz.martlin.douckonline.business.utils.Tools;
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
    private static final Requests requests = new Requests();
    
    private final Subject ENGLISH = new Subject("english", SubjectCategory.FOREIGN_LANGUAGE);
    private final Subject MATH = new Subject("math", SubjectCategory.SCIENCE);
    private final Subject PHYSICS = new Subject("physics", SubjectCategory.SCIENCE);
    private final Subject PIANO = new Subject("piano", SubjectCategory.MUSIC);
    
    private Lector johnsmart;
    private Lector jamiehydrogen;
    private Lector petergeek;
    private Lector janebach;
    
    private Student karllazy;
    private Student mikenerd;
    private Student evemilly;
    
    private Teaching bachMillyPiano;
    private Teaching smartLazyEng;
    private Teaching geekNerdMath;
    private Teaching geekLazyPhys;
    
    private TeachingRequest allPiano;
    private TeachingRequest elemMath;
    private TeachingRequest middEng;
    
    
    public void create() {
	createSubjects();
	
	createLectors();
	createLectorsMeta();
	
	createStudents();
	createManagers();

	createTeaching1();
	createLessons1();
	createPayments1();
	
	createRequests();
	createRequestsReactions();
    }

    private void createSubjects() {
	subjects.addSubject(ENGLISH);
	subjects.addSubject(MATH);
	subjects.addSubject(PHYSICS);
	subjects.addSubject(PIANO);
    }

    private void createLectorsMeta() {
	Certificate fce = new Certificate("FCE", ENGLISH, "C1", null);	
	lectors.addCertificate(johnsmart, fce);
	
	Certificate piano1 = new Certificate("UFSKFMS", PIANO, "virtuos", null);	
	lectors.addCertificate(janebach, piano1);
	
	Education mmath =  new Education("Palacký university", "Metamathematics", "doc.", null);
	lectors.addEducation(petergeek, mmath);
	
	Education nothing1 =  new Education("Charles University, Prague", "Just - nothing", "prof.", null);
	lectors.addEducation(janebach, nothing1);
	
	Education cs = new Education("King's Colleage", "Computer Science", "PhD.", null);
	lectors.addEducation(johnsmart, cs);
	
	Education ing = new Education("My high school", "engeneering", "grad", null);
	lectors.addEducation(jamiehydrogen, ing);
	
	Practice shop = new Practice("counter", "Local shop", null);
	lectors.addPractise(petergeek, shop);
	
	Practice nothing2 = new Practice("doing nothing", "ahome", null);
	lectors.addPractise(janebach, nothing2);
	
	Practice erasmus = new Practice("Erasmus", "U.S.A", null);
	lectors.addPractise(johnsmart, erasmus);

	Practice bells = new Practice("assistant", "Bell's labs", null);
	lectors.addPractise(jamiehydrogen, bells);
	
	SubjTeachingSpec eng = new SubjTeachingSpec(ENGLISH, Level.ALL, Suitability.PRIMARY_SUBJECT, "conversation", 150, null);
	lectors.addSubjTeachSpec(johnsmart, eng);
	
	SubjTeachingSpec piano2 = new SubjTeachingSpec(PIANO, Level.ALL, Suitability.PRIMARY_SUBJECT, "everything", 180, null);
	lectors.addSubjTeachSpec(janebach, piano2);
	
	SubjTeachingSpec math1 = new SubjTeachingSpec(MATH, Level.ONLY_BASIC, Suitability.SECONDARY_SUBJECT, "basics", 100, null);
	lectors.addSubjTeachSpec(petergeek, math1);
	
	SubjTeachingSpec math2 = new SubjTeachingSpec(MATH, Level.BASIC_AND_INTERMIDIATE, Suitability.PRIMARY_SUBJECT, "infinity", 140, null);
	lectors.addSubjTeachSpec(jamiehydrogen, math2);
	
	SubjTeachingSpec phys = new SubjTeachingSpec(PHYSICS, Level.ALL, Suitability.PRIMARY_SUBJECT, "science!", 180, null);
	lectors.addSubjTeachSpec(petergeek, phys);
    }

    private void createLectors() {
	johnsmart = createLector("Mrs.", "John Smart", "PhD.", 
		 "johns-super-password", "john@smart.com", "777 888 999", 
		"+420/066655842/444", 28, 25, 15);
	
	jamiehydrogen = createLector("", "Jamie Hydrogen", "", 
		 "H2-password", "jamie@gmail.com", "565 454 686", 
		null, 19, 19, null);
	
	petergeek = createLector("Bc.", "Peter Geek", "", 
		 "Star-somethin-Password", "peter@geekasm.org", "999 111 222", 
		null, 21, 14, null);
	
	janebach = createLector("", "Jane Bach", "dis.", 
		 "ThePianisimmo", "jane@bach.com", "343 787 000", 
		"0101/4455667788-101", 20, 14, null);
    }
    
    private Lector createLector(String titleBeforeName, String fullName, String titleAfterName, String password, String email, String phone, String bankAccountNumber, int registeredBefore, int lastLoginBefore, Integer endedBefore) {
	List<SubjTeachingSpec> subjects = emptyList();
	List<Certificate> certificates = emptyList();
	List<Education> educations = emptyList();
	List<Practice> practices = emptyList();
	
	Calendar registeredAt = daysAgo(registeredBefore);
	Calendar lastLoginAt = daysAgo(lastLoginBefore);
	Calendar endedAt = endedBefore != null ? daysAgo(endedBefore) : null;
	
	String passwordHash = null;
	String passwordSalt = null;
	String loginName = null;
	
	Lector lector = new Lector(fullName, titleBeforeName, titleAfterName, phone, email, bankAccountNumber, endedAt, subjects, certificates, educations, practices, loginName, passwordHash, passwordSalt, registeredAt, lastLoginAt);
	lectors.addLector(lector, password);
	return lector;
    }

    private void createStudents() {
	karllazy = createStudent("Mary Lazy", "Karl", 
		"774 155 812", "mary@lazy.uk", "15 42th Ave NYC", 
		null, 20, 18);
	
	mikenerd = createStudent("Mike Nerd", "Mike Nerd", 
		"196 804 0308", "mike@nerd.com", "42 Geektown", 
		"1455-47778335/5555", 16, 4);
	
	evemilly = createStudent("Mom Milly", "Eve Milly", 
		"112 233 455", "eve@millies.uk", "The Millies 1, L.A.", 
		"45522-44457775-1161",  20, 20);
    }
    
    private Student createStudent(String registerName, String studentName, String phone, String email, String adress, String bankAccountNumber, int regeristedBefore, int lastLoginBefore) {
	String loginName = null;
	String passwordHash = null;
	String passwordSalt = null;
	Calendar registeredAt = daysAgo(regeristedBefore);
	Calendar lastLoginAt = daysAgo(lastLoginBefore);
	
	Student student = new Student(registerName, studentName, phone, email, bankAccountNumber, adress, loginName, passwordHash, passwordSalt, registeredAt, lastLoginAt);
	students.addStudent(student);
	return student;
    }

    private void createManagers() {

	String loginName = null;
	String password = "ThisIsYetAnotherPassword";
	String passwordHash = null;
	String passwordSalt = null;
	Calendar registeredAt = null;
	Calendar lastLoginAt = null;
	String fullName = "Bill Bossy";

	Manager bill = new Manager(fullName, loginName, passwordHash, passwordSalt, registeredAt, lastLoginAt);
	managers.addManager(bill, password);
    }

    

    private void createTeaching1() {
	//TODO edit statusDescription, some make endedAt ...
	bachMillyPiano = teachings.startTeaching(janebach, evemilly, PIANO, Level.ONLY_BASIC, 140);
	smartLazyEng = teachings.startTeaching(johnsmart, karllazy, ENGLISH, Level.ONLY_INTERMIDIATE, 190);
	geekNerdMath = 	teachings.startTeaching(petergeek, mikenerd, MATH, Level.ALL, 190);
	geekLazyPhys = 	teachings.startTeaching(petergeek, karllazy, PHYSICS, Level.ONLY_BASIC, 120);
    }

    private void createLessons1() {
	teachings.addLesson(bachMillyPiano, daysAgo(15), Tools.time(1, 30), "First look");
	teachings.addLesson(bachMillyPiano, daysAgo(14), Tools.time(1, 30), "First listen");
	teachings.addLesson(bachMillyPiano, daysAgo(3), Tools.time(1, 30), "First touch");

	teachings.addLesson(smartLazyEng, daysAgo(21), Tools.time(0, 45), "Present perfect");
	teachings.addLesson(smartLazyEng, daysAgo(14), Tools.time(0, 45), "Past perfect");
	
	teachings.addLesson(geekNerdMath, daysAgo(11), Tools.time(1, 0), "Linear equations");
	teachings.addLesson(geekNerdMath, daysAgo(10), Tools.time(1, 0), "Nonliear equations");
	
	teachings.addLesson(geekLazyPhys, daysAgo(15), Tools.time(2, 0), "Newton's laws");
    }

    private void createPayments1() {
	moneys.addPayment(evemilly, daysAgo(15), 500, 0);
	moneys.addPayment(evemilly, daysAgo(10), 500, 10);
	
	moneys.addPayment(mikenerd, daysAgo(8), 400, 0);
	
    }

    
    
     public static <T> List<T> emptyList() {
	return new ArrayList<>();
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

    private void createRequests() {
	allPiano = new TeachingRequest("Miss Flute", "Kate Flute", "we@flute.com", "77554589566", PIANO, Level.ONLY_BASIC, "Piano for daughter", null, new ArrayList<RequestReaction>(), TeachingRequestStatus.WAITING_FOR_REACTIONS);
	requests.addRequest(allPiano);
	
	elemMath = new TeachingRequest("Pete Square", "Pete Square", "ptr@sq.com", "7551212662", MATH, Level.BASIC_AND_INTERMIDIATE, "Help!", null, new ArrayList<RequestReaction>(), TeachingRequestStatus.WAITING_FOR_REACTIONS);
	requests.addRequest(elemMath);
	
	middEng = new TeachingRequest("Anne Czech", "Lily Czech", "ann@cz.cz", "48722666688", ENGLISH, Level.ONLY_BASIC, "Bonjour, hilfe!", null, new ArrayList<RequestReaction>(), TeachingRequestStatus.WAITING_FOR_REACTIONS);
	requests.addRequest(middEng);
    }

    private void createRequestsReactions() {
	RequestReaction reaction1 = new RequestReaction(null, jamiehydrogen, RequestReactionStatus.YES, daysAgo(4), "Yes! I'm totally in!");
	requests.react(elemMath, reaction1);
	
	RequestReaction reaction2 = new RequestReaction(null, petergeek, RequestReactionStatus.IF_NO_ONE_ELSE, daysAgo(3), "Okay..!");
	requests.react(elemMath, reaction2);
	
	RequestReaction reaction3 = new RequestReaction(null, johnsmart, RequestReactionStatus.NO, daysAgo(5), "No time, sorry...");
	requests.react(middEng, reaction3);
    }

}
