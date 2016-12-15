package cz.martlin.douckonline.business.model.lector;

import cz.martlin.douckonline.business.model.base.EntityWithLongID;
import cz.martlin.douckonline.business.model.teaching.Subject;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * Represents lector's certificate (i.e. FCE exam).
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
@Entity
@Table(name = "certificates")
public class Certificate extends EntityWithLongID {

    @Column(name = "name")
    @Size(min = 1, max = 50)
    private String name;

    @ManyToOne
    @JoinColumn(name = "subject_name")
    private Subject subject;

    @Column(name = "degree")
    @Size(min = 1, max = 50)
    private String degree;

    @ManyToOne
    @JoinColumn(name = "lector_login_name")
    private Lector lector;

    public Certificate() {
	super();
    }

    public Certificate(String name, Subject subject, String degree, Lector lector) {
	this.name = name;
	this.subject = subject;
	this.degree = degree;
	this.lector = lector;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public Subject getSubject() {
	return subject;
    }

    public void setSubject(Subject subject) {
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
	return "Certificate{" + "id=" + id + ", name=" + name + ", subject=" + subject + ", degree=" + degree + '}';
    }

}
