package cz.martlin.douckonline.business.model.managment;

import cz.martlin.douckonline.business.model.base.EntityWithLongID;
import cz.martlin.douckonline.business.model.teaching.Student;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Student's payment. Links to student and nextly evides amount (suprisingly),
 * date of add and also discount.
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
@Entity
@Table(name = "payments")
public class Payment extends EntityWithLongID {

    @ManyToOne
    @JoinColumn(name = "student_login_name")
    private Student student;

    @Column(name = "amount")
    @Min(0)
    private int amount;

    @Column(name = "discount")
    @Min(0)
    @Max(100)
    private int discount;

    @Column(name = "`date`")
    @Temporal(TemporalType.DATE)
    private Calendar date;

    public Payment() {
	super();

    }

    public Payment(Student student, int amount, int discount, Calendar date) {
	super();

	this.student = student;
	this.amount = amount;
	this.discount = discount;
	this.date = date;
    }

    public Student getStudent() {
	return student;
    }

    public void setStudent(Student student) {
	this.student = student;
    }

    public int getAmount() {
	return amount;
    }

    public void setAmount(int amount) {
	this.amount = amount;
    }

    public int getDiscount() {
	return discount;
    }

    public void setDiscount(int discount) {
	this.discount = discount;
    }

    public Calendar getDate() {
	return date;
    }

    public void setDate(Calendar date) {
	this.date = date;
    }

    @Override
    public String toString() {
	return "Payment{id=" + id + ", amount=" + amount + ", student=" + student + "}";
    }

}
