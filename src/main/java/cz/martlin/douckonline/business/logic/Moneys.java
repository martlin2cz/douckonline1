package cz.martlin.douckonline.business.logic;

import cz.martlin.douckonline.business.model.managment.Payment;
import cz.martlin.douckonline.business.model.teaching.Lesson;
import cz.martlin.douckonline.business.model.teaching.Student;
;
import cz.martlin.douckonline.business.tools.DbLoading;
import cz.martlin.douckonline.business.tools.DbModifying;
import cz.martlin.douckonline.business.utils.Tools;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implements logic operations working with money (payments, ballances and
 * wages).
 *
 * @author m@rtlin <martlin@seznam.cz>
 */


public class Moneys {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final DbLoading dbl = DbLoading.get();
    private final DbModifying dbm = DbModifying.get();

    public Moneys() {
    }

//<editor-fold defaultstate="collapsed" desc="payments">
    /**
     * Lists all payments.
     *
     * @return
     */
    public List<Payment> listAllPayments() {
	LOG.trace("Loading all payments");
	List<Payment> payments = dbl.listAll(Payment.class);
	return payments;
    }

    /**
     * Lists all payments of specified student for given last days.
     *
     * @param student
     * @param daysAgo
     * @return
     */
    public List<Payment> listPayments(Student student, Integer daysAgo) {
	LOG.trace("Loading payments of student for last days");

	List<Payment> payments = dbl.listByCond(Payment.class, false, //
		new Class<?>[]{Student.class}, //
		new String[]{"payment.student"}, //
		new String[]{"student"}, // 
		new Object[]{student});

	return payments;
    }

    /**
     * Adds payment specified by given params.
     *
     * @param student
     * @param date
     * @param amount
     * @param discount
     * @return
     */
    @Deprecated
    public boolean addPayment(Student student, Calendar date, int amount, int discount) {
	LOG.trace("Adds payment");

	Payment payment = new Payment(student, amount, discount, date);
	return dbm.insertSingle(payment);
    }

    /**
     * Adds payment.
     *
     * @param payment
     * @return
     */
    public boolean addPayment(Payment payment) {
	LOG.trace("Adds payment");

	return dbm.insertSingle(payment);
    }

    /**
     * Updates given payment.
     *
     * @param payment
     * @return
     */
    public boolean updatePayment(Payment payment) {
	LOG.trace("Updates payment");

	return dbm.updateSingle(payment);
    }

    /**
     * Removes given payment.
     *
     * @param payment
     * @return
     */
    public boolean removePayment(Payment payment) {
	LOG.trace("Removes payment");

	return dbm.removeSingle(payment);
    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="ballances">
    /**
     * Returns current ballance of student's account
     *
     * @param student
     * @return
     */
    public BigDecimal getBallanceOfStudent(Student student) {
	LOG.trace("Getting ballance of student");

	/*
	//TODO payment.discount
	String paymentsJPQL = "SELECT SUM(payment.amount) FROM Payment payment WHERE payment.student = :student";
	//TODO lesson.duration
	String outcomesJPQL = "SELECT SUM(t.cost) FROM Teaching t INNER JOIN Lesson l ON t.lesson = l WHERE t.student = :student";
	String[] vars = new String[]{"student"};
	Object[] vals = new Object[]{student};
	
	Long payments = db.runNativeJPQL(paymentsJPQL, vars, vals);
	Long outcomes = db.runNativeJPQL(outcomesJPQL, vars, vals);
	
	int ballance = (int) (payments - outcomes);
	return ballance;
	 */
	BigDecimal outcomes = getOutcomesOfStudent(student);
	BigDecimal payments = getPaymentsOfStudent(student);
	BigDecimal sum = outcomes.add(payments);
	return sum;
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="misc">

    /**
     * Gets total payments of student.
     *
     * @param student
     * @return
     */
    public BigDecimal getPaymentsOfStudent(Student student) {
	BigDecimal sum = BigDecimal.ZERO;

	for (Payment payment : listPayments(student, null)) {
	    BigDecimal amount = new BigDecimal(payment.getAmount());
	    BigDecimal steal = Tools.toPercentMultiplier(payment.getDiscount());
	    BigDecimal value = amount.multiply(steal);
	    sum = sum.add(value);
	}

	return sum;
    }

    /**
     * Get outcomes (bought lessons) of student.
     *
     * @param student
     * @return
     */
    public BigDecimal getOutcomesOfStudent(Student student) {
	final Teachings teachings = new Teachings();

	BigDecimal sum = BigDecimal.ZERO;

	for (Lesson lesson : teachings.getLessonsOf(student, null)) {
	    BigDecimal unitCost = new BigDecimal(lesson.getTeaching().getCost());
	    BigDecimal duration = Tools.durationToHours(lesson.getDuration());
	    BigDecimal cost = unitCost.multiply(duration);
	    sum = sum.subtract(cost);
	}

	return sum;
    }
//</editor-fold>
}
