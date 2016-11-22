package cz.martlin.douckonline.business.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
@Entity
@Table(name = "certificates")
public class Certificate implements  Serializable{
    
    @Id
    @GeneratedValue
    private long id;
    
    @Column(name = "name")
    @Size(min = 1, max = 50)
    private String name;
    
    @OneToOne
    private Subject subject;
    
    @Column(name = "degree")
    @Size(min = 1, max = 50)
    private String degree;

    public Certificate() {
    }

    public Certificate(long id, String name, Subject subject, String degree) {
	this.id = id;
	this.name = name;
	this.subject = subject;
	this.degree = degree;
    }

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
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
    public int hashCode() {
	int hash = 3;
	hash = 79 * hash + (int) (this.id ^ (this.id >>> 32));
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
	final Certificate other = (Certificate) obj;
	if (this.id != other.id) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "Certificate{" + "id=" + id + ", name=" + name + ", subject=" + subject + ", degree=" + degree + '}';
    }
    
    
    
}
