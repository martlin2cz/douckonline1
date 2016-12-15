package cz.martlin.douckonline.business.tools;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Perform the root database access.
 * @author m@rtlin <martlin@seznam.cz>
 */
public class DatabaseAccess {
    private final static Logger LOG = LoggerFactory.getLogger(DatabaseAccess.class);

    private final static String PERSISTENCE_UNIT_NAME = "pu_douckonline_1";
    private static final DatabaseAccess INSTANCE = new DatabaseAccess();
    
    
    private static EntityManagerFactory entityManagerFactory;
    
    private static EntityManager entityManager;
    
    /**
     * Singleton.
     */
    private DatabaseAccess() {
    }

    
    /**
     * Returns the only one instance.
     * @return 
     */
    public static DatabaseAccess get() {
	return INSTANCE;
    }

    
//<editor-fold defaultstate="collapsed" desc="start and finish of whole db mechanism">
    /**
     * Starts whole database work.
     */
    public static void startupDatabase() {
	LOG.info("Starting entity manager factory");
	entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	entityManager = entityManagerFactory.createEntityManager();
    }

    /**
     * Finished the database.
     */
    public static void finishDatabase() {
	LOG.info("Closing entity manager factory");
	entityManager = null;
	entityManagerFactory.close();
	
    }
    
    /**
     * Returns entity manager of current connection.
     * @return 
     */
    public EntityManager getEntityManager() {
	return entityManager;
    }
//</editor-fold>
}
