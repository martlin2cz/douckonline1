package cz.martlin.douckonline.business.data;

import cz.martlin.douckonline.business.model.managment.Payment;
import cz.martlin.douckonline.business.model.teaching.Student;
import cz.martlin.douckonline.business.model.teaching.Teaching;
import cz.martlin.douckonline.business.tools.DbAccessor;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
public class Moneys {
    private final DbAccessor db = new DbAccessor();

    public Moneys() {
    }
    
    public List<Payment> listPayments(Student student, int daysAgo) {
	Class<?>[] froms = {Payment.class, Student.class,};
	String[] attrs = {"payment.student",  };
	String[] vars = { "student", };  
	Object[] values = { student};
	//TODO daysAgo
	
	return db.listBySimpleCond(Payment.class, froms, attrs, vars, values);
    }
    
    public boolean addPayment(Student student, Calendar date, int amount, int discount) {
	
	Payment payment = new Payment(0, student, amount, discount, date);
	return db.insert(payment);
    }
    
}
