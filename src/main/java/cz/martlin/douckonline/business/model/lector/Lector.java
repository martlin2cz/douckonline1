package cz.martlin.douckonline.business.model.lector;

import cz.martlin.douckonline.business.model.managment.User;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
@Entity
@Table(name = "lectors")
public class Lector extends User implements Serializable {

    @Column(name = "full_name")
    @Size(min = 3, max = 80)
    private String fullName;
    
    @Column(name = "title_before_name")
    @Size(min = 0, max = 20)
    private String titleBeforeName;
    
    @Column(name = "title_after_name")
    @Size(min = 0, max = 20)
    private String titleAfterName;
    
    @Column(name = "phone")
    @Size(min = 8, max = 15)
    private String phone;
    
    @Column(name = "email")
    @Size(min = 0, max = 20)
    private String email;
    
    @Column(name = "bank_account_number")
    @Size(min = 8, max = 20)
    //@Nullable
    private String bankAccountNumber;
    
    
    @OneToMany
    private List<SubjTeachingSpec> subjects;
    
    @OneToMany
    private List<Certificate> certificates;
    
    @OneToMany
    private List<Education> educations;

    @OneToMany
    private List<Practice> experiences;
    
    
    public Lector() {
	super();
    }

    public Lector(String fullName, String titleBeforeName, String titleAfterName, String phone, String email, String bankAccountNumber, List<SubjTeachingSpec> subjects, List<Certificate> certificates, List<Education> educations, List<Practice> experiences, String loginName, String passwordHash, String passwordSalt, Calendar registeredAt, Calendar lastLoginAt) {
	super(loginName, passwordHash, passwordSalt, registeredAt, lastLoginAt);
	this.fullName = fullName;
	this.titleBeforeName = titleBeforeName;
	this.titleAfterName = titleAfterName;
	this.phone = phone;
	this.email = email;
	this.bankAccountNumber = bankAccountNumber;
	this.subjects = subjects;
	this.certificates = certificates;
	this.educations = educations;
	this.experiences = experiences;
    }

    public String getFullName() {
	return fullName;
    }

    public void setFullName(String fullName) {
	this.fullName = fullName;
    }

    public String getTitleBeforeName() {
	return titleBeforeName;
    }

    public void setTitleBeforeName(String titleBeforeName) {
	this.titleBeforeName = titleBeforeName;
    }

    public String getTitleAfterName() {
	return titleAfterName;
    }

    public void setTitleAfterName(String titleAfterName) {
	this.titleAfterName = titleAfterName;
    }

    public String getPhone() {
	return phone;
    }

    public void setPhone(String phone) {
	this.phone = phone;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getBankAccountNumber() {
	return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
	this.bankAccountNumber = bankAccountNumber;
    }

    public List<SubjTeachingSpec> getSubjects() {
	return subjects;
    }

    public void setSubjects(List<SubjTeachingSpec> subjects) {
	this.subjects = subjects;
    }

    public List<Certificate> getCertificates() {
	return certificates;
    }

    public void setCertificates(List<Certificate> certificates) {
	this.certificates = certificates;
    }

    public List<Education> getEducations() {
	return educations;
    }

    public void setEducations(List<Education> educations) {
	this.educations = educations;
    }

    public List<Practice> getExperiences() {
	return experiences;
    }

    public void setExperiences(List<Practice> experiences) {
	this.experiences = experiences;
    }
 
    @Override
    public String toString() {
	return "Lector{" + getLoginName() + "}";
    }
        
}