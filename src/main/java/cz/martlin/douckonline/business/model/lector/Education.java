package cz.martlin.douckonline.business.model.lector;

import cz.martlin.douckonline.business.model.base.EntityWithLongID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
@Entity
@Table(name = "education")
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

    public Education() {
	super();
    }

    public Education(String institution, String subject, String degree) {
	super();
	this.institution = institution;
	this.subject = subject;
	this.degree = degree;
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

    @Override
    public String toString() {
	return "Education{" + "id=" + id + ", institution=" + institution + ", subject=" + subject + ", degree=" + degree + '}';
    }

}
