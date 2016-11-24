package cz.martlin.douckonline.business.model.lector;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
@Entity
@Table(name = "education")
public class Education  implements Serializable {
    
    @Id
    @GeneratedValue
    private long id;
    
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
    }

    public Education(long id, String institution, String subject, String degree) {
	this.id = id;
	this.institution = institution;
	this.subject = subject;
	this.degree = degree;
    }


    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
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
	final Education other = (Education) obj;
	if (this.id != other.id) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "Education{" + "id=" + id + ", institution=" + institution + ", subject=" + subject + ", degree=" + degree + '}';
    }

    
    
}
