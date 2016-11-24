package cz.martlin.douckonline.business.model.teaching;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
@Entity
@Table(name = "subjects")
public class Subject implements Serializable {

    @Id
    @Column(name = "name")
    @Size(min = 1, max = 30)
    private String name;

    public Subject() {
    }

    public Subject(String name) {
	this.name = name;
    }
    
    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    @Override
    public int hashCode() {
	int hash = 3;
	hash = 47 * hash + Objects.hashCode(this.name);
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
	final Subject other = (Subject) obj;
	if (!Objects.equals(this.name, other.name)) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "Subject{" + name + "}";
    }
    
}