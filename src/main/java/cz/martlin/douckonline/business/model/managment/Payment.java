package cz.martlin.douckonline.business.model.managment;

import cz.martlin.douckonline.business.model.teaching.Student;
import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
@Entity
@Table( name = "payments")
public class Payment implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private Student student;
    
    @Min(0)
    private int amount;
    
    @Min(0)
    @Max(100)
    private int discount;
    
    @Temporal(TemporalType.DATE)
    private Calendar date;

    public Payment() {
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
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
    public int hashCode() {
	int hash = 5;
	hash = 97 * hash + this.id;
	return hash;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (obj == null) {
	    return false;
	}
	if (getClass() != obj.getClass()) {
	    return false;
	}
	final Payment other = (Payment) obj;
	if (this.id != other.id) {
	    return false;
	}
	return true;
    }

    
    
    
    @Override
    public String toString() {
	return "Payment{id=" + id + "}";
    }
    
}
