package cz.martlin.douckonline.business.tools;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
public class DatabaseAccess {
    private final static Logger LOG = LoggerFactory.getLogger(DatabaseAccess.class);

    private final static String PERSISTENCE_UNIT_NAME = "pu_douckonline_1";

    private static EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public DatabaseAccess() {
    }

//<editor-fold defaultstate="collapsed" desc="start and finish of whole db mechanism">
    public static void createFactory() {
	LOG.info("Starting entity manager factory");
	entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    public static void closeFactory() {
	LOG.info("Closing entity manager factory");
	entityManagerFactory.close();
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="setup and unset entity manager">
    
    public EntityManager getEntityManager() {
	return entityManager;
    }
    
    public boolean isEntityManagerSet() {
	return entityManager != null;
    }
    
    public void setupEntityManager() {
	LOG.trace("Setting up entity manager");
	entityManager = entityManagerFactory.createEntityManager();
    }
    
    public void unsetEntityManager() {
	LOG.trace("Unsetting entity manager");
	entityManager = null;
    }
//</editor-fold>
}
