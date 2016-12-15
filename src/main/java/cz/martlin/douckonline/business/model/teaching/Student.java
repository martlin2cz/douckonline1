package cz.martlin.douckonline.business.model.teaching;


import cz.martlin.douckonline.business.model.base.User;
import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Student entity. Each student has user's credentials, but also a contact informations (name, adress, email, phone) and optionally bank account.
 * @author m@rtlin <martlin@seznam.cz>
 */
@Entity
@Table(name = "students")
@XmlRootElement
public class Student extends User implements Serializable{
    
    @Column(name = "register_name")
    @Size(min = 2, max = 50)
    private String registerName;
    
    @Column(name = "student_name")
    @Size(min = 2, max = 50)
    private String studentName;
    
    @Column(name = "email")
    @Size(min = 2, max = 50)
    private String email;
    
    @Column(name = "phone")
    @Size(min = 9, max = 15)
    private String phone;
    
    @Column(name = "bank_account_number", nullable = true)
    @Size(min = 8, max = 20)
    private String bankAccountNumber;
    
    @Column(name = "adress", nullable = true)
    @Size(min = 5, max = 50)
    private String adress;
    
    public Student() {
    }

    public Student(String registerName, String studentName, String email, String phone, String bankAccountNumber, String adress, String loginName, String passwordHash, String passwordSalt, Calendar registeredAt, Calendar lastLoginAt) {
	super(loginName, passwordHash, passwordSalt, registeredAt, lastLoginAt);
	this.registerName = registerName;
	this.studentName = studentName;
	this.email = email;
	this.phone = phone;
	this.bankAccountNumber = bankAccountNumber;
	this.adress = adress;
    }

    @Override
    public String getDisplayName() {
	return registerName;
    }

    @Override
    public String getReallName() {
	return registerName;
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

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getPhone() {
	return phone;
    }

    public void setPhone(String phone) {
	this.phone = phone;
    }

    public String getBankAccountNumber() {
	return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
	this.bankAccountNumber = bankAccountNumber;
    }

    public String getAdress() {
	return adress;
    }

    public void setAdress(String adress) {
	this.adress = adress;
    }
    
    @Override
    public String toString() {
	return "Student{" +  getLoginName() + "}";
    }
}
