package cz.martlin.douckonline.business.model.base;

import cz.martlin.douckonline.business.model.managment.TeachingRequest;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
@MappedSuperclass
public abstract class EntityWithLongID implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;

    public EntityWithLongID() {
    }

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }
    
    public boolean isPersisted() {
	return this.id != 0;
    }
    
    @Override
    public int hashCode() {
	int hash = 0;
	hash += (int) id;
	return hash;
    }

    @Override
    public boolean equals(Object object) {
	if (!(object instanceof TeachingRequest)) {
	    return false;
	}
	TeachingRequest other = (TeachingRequest) object;
	if (this.id != other.id) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "EntityWithLongID{ id = " + id + " }";
    }
    
}
