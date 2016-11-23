package cz.martlin.douckonline.business.model;

import com.sun.javafx.applet.ExperimentalExtensions;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
    
    @OneToMany
    private List<Subject> subjects; //TODO List<SubjectSomething> ?
    
    @OneToMany
    private List<Certificate> certificates;
    
    @OneToMany
    private List<Education> educations;

    @OneToMany
    private List<Practice> experiences;
    
    //TODO account number
    
    
    public Lector() {
	super();
    }

    public Lector(String fullName, String titleBeforeName, String titleAfterName, List<Subject> subjects, List<Certificate> certificates, List<Education> educations, List<Practice> experiences, String loginName, String passwordHash, String passwordSalt, Calendar registeredAt, Calendar lastLoginAt) {
	super(loginName, passwordHash, passwordSalt, registeredAt, lastLoginAt);
	this.fullName = fullName;
	this.titleBeforeName = titleBeforeName;
	this.titleAfterName = titleAfterName;
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

    public List<Subject> getSubjects() {
	return subjects;
    }
    
    public void addSubject(Subject subject) {
	subjects.add(subject);
    }

    public void setSubjects(List<Subject> subjects) {
	this.subjects = subjects;
    }

    public List<Certificate> getCertificates() {
	return certificates;
    }

    public void setCertificates(List<Certificate> certificates) {
	this.certificates = certificates;
    }
    
    
    public void addCertificate(Certificate certificate) {
	certificates.add(certificate);
    }

    public List<Education> getEducations() {
	return educations;
    }

    public void setEducations(List<Education> educations) {
	this.educations = educations;
    }
    
    
    public void addEducation(Education education) {
	educations.add(education);
    }

    public List<Practice> getExperiences() {
	return experiences;
    }

    public void setExperiences(List<Practice> experiences) {
	this.experiences = experiences;
    }
    
    
    public void addExperience(Practice practice) {
	experiences.add(practice);
    }
    
    @Override
    public String toString() {
	return "Lector{" + getLoginName() + "}";
    }
    
    
}
