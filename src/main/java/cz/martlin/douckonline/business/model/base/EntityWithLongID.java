package cz.martlin.douckonline.business.model.base;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Base entity class with <code>long</code> id field.
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
	if (!(object instanceof EntityWithLongID)) {
	    return false;
	}
	EntityWithLongID other = (EntityWithLongID) object;
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
