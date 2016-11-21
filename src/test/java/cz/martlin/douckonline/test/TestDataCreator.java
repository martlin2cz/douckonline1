package cz.martlin.douckonline.test;

import cz.martlin.douckonline.business.data.Lectors;
import cz.martlin.douckonline.business.model.Lector;

/**
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
public class TestDataCreator {
    
    public void create() {
	createLectors();
    }

    //TODO

    private void createLectors() {
	Lectors lectors = new Lectors();
	
	lectors.addLector(new Lector("John"));
	lectors.addLector(new Lector("Jimmy"));
	lectors.addLector(new Lector("Bill"));
	lectors.addLector(new Lector("John"));
	
    }
    
//    public static void main(String[] args) {
//	new TestDataCreator().create();
//    }
}
