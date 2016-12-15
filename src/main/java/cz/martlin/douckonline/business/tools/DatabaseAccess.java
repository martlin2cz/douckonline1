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
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
public class DatabaseAccess {
    private final static Logger LOG = LoggerFactory.getLogger(DatabaseAccess.class);

    private final static String PERSISTENCE_UNIT_NAME = "pu_douckonline_1";
    private static final DatabaseAccess INSTANCE = new DatabaseAccess();
    
    
    private static EntityManagerFactory entityManagerFactory;
    
    //@PersistenceContext(unitName = PERSISTENCE_UNIT_NAME)
    private static EntityManager entityManager;
    
//    @Inject
//    private UserTransaction transaction;

    private DatabaseAccess() {
    }

    
    public static DatabaseAccess get() {
	return INSTANCE;
    }

    
//<editor-fold defaultstate="collapsed" desc="start and finish of whole db mechanism">
    public static void createFactory() {
	LOG.info("Starting entity manager factory");
	entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	entityManager = entityManagerFactory.createEntityManager();
    }

    public static void closeFactory() {
	LOG.info("Closing entity manager factory");
	entityManager = null;
	entityManagerFactory.close();
	
    }
    
    public EntityManager getEntityManager() {
	return entityManager;
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="setup and unset entity manager">

 /*   
    @Deprecated
    public boolean isEntityManagerSet() {
	return entityManager != null;
    }
    
    @Deprecated
    public void setupEntityManager() {
	LOG.trace("Setting up entity manager");
	
    }
    
    @Deprecated
    public void unsetEntityManager() {
	LOG.trace("Unsetting entity manager");
	
    }
    
    
//</editor-fold>

   public UserTransaction getTransaction() {
throw new UnsupportedOperationException("JTA transaction, foo");
//return transaction;
    }
*/
}
