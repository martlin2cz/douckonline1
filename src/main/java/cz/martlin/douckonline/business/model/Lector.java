package cz.martlin.douckonline.business.model;

import java.io.Serializable;

/**
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
public class Lector implements  Serializable {
    
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
