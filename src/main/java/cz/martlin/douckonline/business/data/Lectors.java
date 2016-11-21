package cz.martlin.douckonline.business.data;

import cz.martlin.douckonline.business.model.Lector;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
public class Lectors {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    public Lectors() {
    }
    
    
    
    
    public List<Lector> listAllLectors() {
	LOG.debug("Loading lectors");
	List<Lector> lectors = new ArrayList<>();
	
	lectors.add(new Lector("John"));
	lectors.add(new Lector("Bill"));
	lectors.add(new Lector("Jimmy"));
	
	return lectors;
    }
}

