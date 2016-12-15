
package cz.martlin.douckonline.web.controllers;

import cz.martlin.douckonline.business.logic.Moneys;
import cz.martlin.douckonline.business.logic.Students;
import cz.martlin.douckonline.business.model.managment.Payment;
import cz.martlin.douckonline.business.model.teaching.Student;
import cz.martlin.douckonline.web.utils.JSFTools;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
@RequestScoped
@Named("paymentsController")
public class PaymentsController {

    private final Moneys MONEYS = new Moneys();
   
    private List<Payment> payments;
    private Student filterStudent;
    
    private Payment paymentToOpen;
    
    public PaymentsController() {
	paymentToOpen = new Payment();
    }
    
    @PostConstruct
    public void init() {
	loadPayments();
    }
//<editor-fold defaultstate="collapsed" desc="getters and setters">
    
    public List<Payment> getPayments() {
	return payments;
    }
    
    public void setPayments(List<Payment> payments) {
	this.payments = payments;
    }
    
    
    public Student getFilterStudent() {
	return filterStudent;
    }
    
    public void setFilterStudent(Student filterStudent) {
	this.filterStudent = filterStudent;
    }

    public Payment getPaymentToOpen() {
	return paymentToOpen;
    }
    
    
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="action methods">
    
    
    public void filter() {
	loadPayments();
    }
    
    private void loadPayments() {
	if (filterStudent != null) {
	    payments = MONEYS.listPayments(filterStudent, null);
	} else {
	    payments = MONEYS.listAllPayments();
	}
    }
    
    public void openNewPayment() {
	//does nothing
    }
    
    
    public void openPayment(Payment payment) {
	paymentToOpen = payment;
    }
    
    public void savePayment(Payment payment) {
	boolean success;
	if (payment.isPersisted()) {
	    success = MONEYS.updatePayment(payment);
	} else {
	    success = MONEYS.addPayment(payment);
	}
	
	JSFTools.savedOrFailed(success, "Payment saved", "Payment save failed");
    } 
    
    public void removePayment(Payment payment) {
	boolean success = MONEYS.removePayment(payment);
	
	JSFTools.savedOrFailed(success, "Payment removed", "Payment removal failed");
    } 
    
    
//</editor-fold>
    
}
