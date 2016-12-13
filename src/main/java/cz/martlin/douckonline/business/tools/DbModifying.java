package cz.martlin.douckonline.business.tools;

import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
public class DbModifying {
    private final static Logger LOG = LoggerFactory.getLogger(DbModifying.class);
    private static final DbModifying INSTANCE = new DbModifying();
    private final DatabaseAccess db = new DatabaseAccess();

    private boolean success = true;
    private int bulksStack = 0;
    
//<editor-fold defaultstate="collapsed" desc="singleton">
    private DbModifying() {

    }

    public static DbModifying get() {
	return INSTANCE;
    }

//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="bulk of modifications">
    public void startBulk() {
	bulksStack++;
	
	if (bulksStack > 1) {
	    return;
	}
	
	try {
	    db.setupEntityManager();
	    db.getEntityManager().getTransaction().begin();
	    success &= true;
	} catch (Exception e) {
	    LOG.error("Cannot start bulk", e);
	    success = false;
	}
    }

    public void finishBulk() {
	bulksStack--;
	
	if (bulksStack > 0) {
	    return;
	}
	if (bulksStack < 0) {
	    LOG.warn("Finishing not started bulk");
	    return;
	}
	
	try {
	    db.getEntityManager().getTransaction().commit();
	    db.unsetEntityManager();
	    success &= true;
	} catch (Exception e) {
	    LOG.error("Cannot finish bulk", e);
	    success = false;
	}
    }

    public boolean isBulkReady(String operation) {
	boolean ready = bulksStack > 0;

	if (!ready) {
	    Exception e = new RuntimeException("Cannot perform " + operation + ", bulk not ready");
	    LOG.error("Bulk not ready", e);
	}

	return ready;
    }
    
    public boolean isSuccessfull() {
	return success;
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="insert, update, delete">
    public <T> boolean insert(T item) {
	if (!isBulkReady("insert")) {
	    return false;
	}

	try {
	    validate(item);
	    db.getEntityManager().persist(item);
	    success &= true;
	    return success;
	} catch (Exception e) {
	    LOG.error("Cannot insert " + item, e);
	    success = false;
	    return false;
	}
    }

    public <T> boolean update(T item) {
	if (!isBulkReady("update")) {
	    return false;
	}

	try {
	    validate(item);
	    db.getEntityManager().merge(item);
	    success &= true;
	    return success;
	} catch (Exception e) {
	    LOG.error("Cannot update " + item, e);
	    success = false;
	    return false;
	}
    }

    public <T> boolean remove(T item) {
	if (!isBulkReady("remove")) {
	    return false;
	}

	try {
	    //validate(item);
	    db.getEntityManager().remove(item);
	    success &= true;
	    return success;
	} catch (Exception e) {
	    LOG.error("Cannot remove " + item, e);
	    success = false;
	    return false;
	}
    }

    public <T> boolean insertSingle(T item) {
	startBulk();
	insert(item);
	finishBulk();
	return success;
    }

    public <T> boolean updateSingle(T item) {
	startBulk();
	update(item);
	finishBulk();
	return success;
    }

    public <T> boolean removeSingle(T item) {
	startBulk();
	remove(item);
	finishBulk();
	return success;
    }
//</editor-fold>

    /**
     * https://coderanch.com/t/589060/java/bean-validation-error-JPA-persist
     *
     * @param <T>
     * @param entity
     * @return
     */
    private <T> boolean validate(T entity) {
	LOG.info("Validating entity: " + entity);

	ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	Validator validator = factory.getValidator();
	Set<ConstraintViolation<T>> constraintViolations = validator.validate(entity);

	if (!constraintViolations.isEmpty()) {
	    for (ConstraintViolation<T> cv : constraintViolations) {
		LOG.error("Validation error: on " + cv.getPropertyPath() + " value " + cv.getInvalidValue() + ", because: " + cv.getMessage());
	    }
	} else {
	    LOG.info("Validation successfull!");
	}

	return constraintViolations.isEmpty();
    }
}
