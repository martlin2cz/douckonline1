package cz.martlin.douckonline.business.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
@Entity
@Table(name = "lectors")
public class Lector implements Serializable {
    
    @Id
    private String name;

    public Lector() {
    }

    public Lector(String name) {
	this.name = name;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    
    @Override
    public String toString() {
	return "Lector: " + name;
    }
    
    
    
}
