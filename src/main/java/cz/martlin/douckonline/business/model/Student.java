package cz.martlin.douckonline.business.model;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
@Entity
@Table(name = "students")
public class Student extends User implements Serializable{
    
    private String registerName;
    private String studentName;
    private int bankAccountNumber;

    public Student() {
    }

    public Student(String registerName, String studentName, int bankAccountNumber, String loginName, String passwordHash, String passwordSalt, Calendar registeredAt, Calendar lastLoginAt) {
	super(loginName, passwordHash, passwordSalt, registeredAt, lastLoginAt);
	this.registerName = registerName;
	this.studentName = studentName;
	this.bankAccountNumber = bankAccountNumber;
    }

    public String getRegisterName() {
	return registerName;
    }

    public void setRegisterName(String registerName) {
	this.registerName = registerName;
    }

    public String getStudentName() {
	return studentName;
    }

    public void setStudentName(String studentName) {
	this.studentName = studentName;
    }

    public int getBankAccountNumber() {
	return bankAccountNumber;
    }

    public void setBankAccountNumber(int bankAccountNumber) {
	this.bankAccountNumber = bankAccountNumber;
    }

    @Override
    public String toString() {
	return "Student{" +  getLoginName() + "}";
    }
}
