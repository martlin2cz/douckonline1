package cz.martlin.douckonline.business.model.lector;

import cz.martlin.douckonline.business.model.base.EntityWithLongID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
@Entity
@Table(name = "educations")
public class Education extends EntityWithLongID {

    @Column(name = "institution")
    @Size(min = 10, max = 80)
    private String institution;

    @Column(name = "subject")
    @Size(min = 10, max = 80)
    private String subject;

    @Column(name = "degree")
    @Size(min = 1, max = 20)
    private String degree;
    
    @ManyToOne
    @JoinColumn(name = "lector_login_name")
    private Lector lector;

    public Education() {
	super();
    }

    public Education(String institution, String subject, String degree, Lector lector) {
	this.institution = institution;
	this.subject = subject;
	this.degree = degree;
	this.lector = lector;
    }

    public String getInstitution() {
	return institution;
    }

    public void setInstitution(String institution) {
	this.institution = institution;
    }

    public String getSubject() {
	return subject;
    }

    public void setSubject(String subject) {
	this.subject = subject;
    }

    public String getDegree() {
	return degree;
    }

    public void setDegree(String degree) {
	this.degree = degree;
    }

    public Lector getLector() {
	return lector;
    }

    public void setLector(Lector lector) {
	this.lector = lector;
    }

    

    @Override
    public String toString() {
	return "Education{" + "id=" + id + ", institution=" + institution + ", subject=" + subject + ", degree=" + degree + '}';
    }

}
