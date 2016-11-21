package cz.martlin.douckonline.business.data;

import cz.martlin.douckonline.business.model.Lector;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
public class Lectors {
    
    public List<Lector> listAllLectors() {
	List<Lector> lectors = new ArrayList<>();
	
	lectors.add(new Lector("John"));
	lectors.add(new Lector("Bill"));
	lectors.add(new Lector("Jimmy"));
	
	return lectors;
    }
}

