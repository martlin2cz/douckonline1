package cz.martlin.douckonline.business.logic;

import cz.martlin.douckonline.business.model.managment.Payment;
import cz.martlin.douckonline.business.model.teaching.Student;
import cz.martlin.douckonline.business.model.teaching.Teaching;
import cz.martlin.douckonline.business.tools.DbAccessor;
import java.util.Calendar;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
public class Moneys {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final DbAccessor db = new DbAccessor();

    public Moneys() {
    }
    
//<editor-fold defaultstate="collapsed" desc="payments">
    
    /**
     * Lists all payments.
     * @return 
     */
    public List<Payment> listAllPayments() {
	LOG.trace("Loading all payments");
	List<Payment> payments = db.listAll(Payment.class);
	return payments;
    }
    
    /**
     * Lists all payments of specified student for given last days.
     * @param student
     * @param daysAgo
     * @return 
     */
    public List<Payment> listPayments(Student student, int daysAgo) {
	LOG.trace("Loading payments of student for last days");
	
	List<Payment> payments = db.listByCond(Payment.class, //
		new Class<?>[] {Student.class}, //
		new String[]{"payment.student"}, //
		new String[]{"student"}, // 
		new Object[] {student});
	
	return payments;
    }
    
    /**
     * Adds payment specified by given params.
     * @param student
     * @param date
     * @param amount
     * @param discount
     * @return 
     */
    public boolean addPayment(Student student, Calendar date, int amount, int discount) {
	LOG.trace("Adds payment");
	
	Payment payment = new Payment(student, amount, discount, date);
	return db.insert(payment);
    }
    
    /**
     * Removes given payment.
     * @param payment
     * @return 
     */
    public boolean removePayment(Payment payment) {
	LOG.trace("Removes payment");
	
	return db.remove(payment);
    }
    
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="ballances">
    
    /**
     * Returns current ballance of student's account
     * @param student
     * @return 
     */
    public int getBallanceOfStudent(Student student) {
	LOG.trace("Getting ballance of student");
	LOG.warn("not implemented, returns 42");
	
	return 42;  //TODO FIXME
	
    }
//</editor-fold>
}
