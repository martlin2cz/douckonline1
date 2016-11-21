package cz.martlin.douckonline.business.data;

import cz.martlin.douckonline.business.model.Lector;
import cz.martlin.douckonline.business.tools.DbAccessor;
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

    private final DbAccessor db = new DbAccessor();
    
    public Lectors() {
    }
    
    
    
    
    public List<Lector> listAllLectors() {
	LOG.debug("Loading lectors");
	List<Lector> lectors = db.listAll(Lector.class);
	return lectors;
    }
    
    
    public boolean addLector(Lector lector) {
	LOG.debug("Adding lector");
	return db.insert(lector);
    }
}

