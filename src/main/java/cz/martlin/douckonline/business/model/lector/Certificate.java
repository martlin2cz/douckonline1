package cz.martlin.douckonline.business.model.lector;

import cz.martlin.douckonline.business.model.base.EntityWithLongID;
import cz.martlin.douckonline.business.model.teaching.Subject;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
@Entity
@Table(name = "certificates")
public class Certificate extends EntityWithLongID {

    @Column(name = "name")
    @Size(min = 1, max = 50)
    private String name;

    @OneToOne
    private Subject subject;

    @Column(name = "degree")
    @Size(min = 1, max = 50)
    private String degree;

    public Certificate() {
	super();
    }

    public Certificate(String name, Subject subject, String degree) {
	super();
	this.name = name;
	this.subject = subject;
	this.degree = degree;
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

    @Override
    public String toString() {
	return "Certificate{" + "id=" + id + ", name=" + name + ", subject=" + subject + ", degree=" + degree + '}';
    }

}
