package cz.martlin.douckonline.business.model;

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
@Table(name = "practices")
public class Practice implements Serializable {
        
    @Id
    @GeneratedValue
    private long id;
    
    
    @Column(name = "work")
    @Size(min = 1, max = 20)
    private String work;
    
    @Column(name = "institution")
    @Size(min = 0, max = 80)
    private String institution;
    

    public Practice() {
    }

    public Practice(long id, String work, String institution) {
	this.id = id;
	this.work = work;
	this.institution = institution;
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

    public String getWork() {
	return work;
    }

    public void setWork(String work) {
	this.work = work;
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
	final Practice other = (Practice) obj;
	if (this.id != other.id) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "Practice{" + "id=" + id + ", work=" + work + ", institution=" + institution + '}';
    }

    
    
}
