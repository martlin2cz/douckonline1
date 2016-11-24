package cz.martlin.douckonline.business.data;

import cz.martlin.douckonline.business.model.lector.Certificate;
import cz.martlin.douckonline.business.model.lector.Education;
import cz.martlin.douckonline.business.model.lector.Lector;
import cz.martlin.douckonline.business.model.lector.Practice;
import cz.martlin.douckonline.business.model.lector.SubjTeachingSpec;
import cz.martlin.douckonline.business.tools.DbAccessor;
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

    public boolean addCertificate(Certificate certificate) {
	LOG.debug("Adding certificate");
	return db.insert(certificate);
    }

    public boolean addEducation(Education education) {
	LOG.debug("Adding education");
	return db.insert(education);
    }

    public boolean addPractise(Practice practice) {
	LOG.debug("Adding practise");
	return db.insert(practice);
    }

    public boolean addSubjTeachSpec(SubjTeachingSpec spec) {
	LOG.debug("Adding subject teaching spec");
	return db.insert(spec);
    }

    public Lector getLector(String loginName) {
	return db.getById(Lector.class, loginName);
    }
}

