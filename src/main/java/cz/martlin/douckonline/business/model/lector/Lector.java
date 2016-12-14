package cz.martlin.douckonline.business.model.lector;

import cz.martlin.douckonline.business.model.base.User;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
@Entity
@Table(name = "lectors")
@XmlRootElement
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
    
    @Column(name = "bank_account_number", nullable = true)
    @Size(min = 8, max = 20)
    private String bankAccountNumber;
    
    
    @Column(name = "ended_at")
    @Temporal(TemporalType.DATE)
    private Calendar endedAt;
    
    @OneToMany(mappedBy = "lector")
    @JoinColumn(name = "lector_login_name")
    private List<SubjTeachingSpec> subjects;
    
    @OneToMany(mappedBy = "lector")
    @JoinColumn(name = "lector_login_name")
    private List<Certificate> certificates;
    
    @OneToMany(mappedBy = "lector")
    @JoinColumn(name = "lector_login_name")
    private List<Education> educations;

    @OneToMany(mappedBy = "lector")
    @JoinColumn(name = "lector_login_name")
    private List<Practice> practices;
    
    
    public Lector() {
	super();
    }

    public Lector(String fullName, String titleBeforeName, String titleAfterName, String phone, String email, String bankAccountNumber, Calendar endedAt, List<SubjTeachingSpec> subjects, List<Certificate> certificates, List<Education> educations, List<Practice> practices, String loginName, String passwordHash, String passwordSalt, Calendar registeredAt, Calendar lastLoginAt) {
	super(loginName, passwordHash, passwordSalt, registeredAt, lastLoginAt);
	this.fullName = fullName;
	this.titleBeforeName = titleBeforeName;
	this.titleAfterName = titleAfterName;
	this.phone = phone;
	this.email = email;
	this.bankAccountNumber = bankAccountNumber;
	this.endedAt = endedAt;
	this.subjects = subjects;
	this.certificates = certificates;
	this.educations = educations;
	this.practices = practices;
    }

    @Override
    public String getDisplayName() {
	return titleBeforeName + " " + fullName + ", " + titleAfterName;    //TODO outsource?
    }

    @Override
    public String getReallName() {
	return fullName;
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

    public Calendar getEndedAt() {
	return endedAt;
    }

    public void setEndedAt(Calendar endedAt) {
	this.endedAt = endedAt;
    }
    
    @XmlTransient
    public List<SubjTeachingSpec> getSubjects() {
	return subjects;
    }

    public void setSubjects(List<SubjTeachingSpec> subjects) {
	this.subjects = subjects;
    }

    @XmlTransient
    public List<Certificate> getCertificates() {
	return certificates;
    }

    public void setCertificates(List<Certificate> certificates) {
	this.certificates = certificates;
    }

    @XmlTransient
    public List<Education> getEducations() {
	return educations;
    }

    public void setEducations(List<Education> educations) {
	this.educations = educations;
    }

    @XmlTransient
    public List<Practice> getPractices() {
	return practices;
    }

    public void setPractices(List<Practice> practices) {
	this.practices = practices;
    }
  
    @Override
    public String toString() {
	return "Lector{" + getLoginName() + "}";
    }

    
        
}
