package cz.martlin.douckonline.business.tools;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class performing updating (insert, update, delete) on database.
 * @author m@rtlin <martlin@seznam.cz>
 */
public class DbModifying {

    private final static Logger LOG = LoggerFactory.getLogger(DbModifying.class);
    private static final DbModifying INSTANCE = new DbModifying();
    private final DatabaseAccess db = DatabaseAccess.get();

    private boolean success = true;
    private int bulksStack = 0;

//<editor-fold defaultstate="collapsed" desc="singleton">
    /**
     * Singleton.
     */
    private DbModifying() {

    }

    /**
     * Returns the only and only instance.
     * @return 
     */
    public static DbModifying get() {
	return INSTANCE;
    }

//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="bulk of modifications">
    /**
     * Starts bulk of modificaitions.
     */
    public void startBulk() {
	bulksStack++;

	if (bulksStack > 1) {
	    return;
	}

	try {
	    db.getEntityManager().getTransaction().begin();
	    success &= true;
	} catch (Exception e) {
	    LOG.error("Cannot start bulk", e);
	    success = false;
	}
    }

    /**
     * Finishs bulk of modifications.
     */
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
	    success &= true;
	} catch (Exception e) {
	    LOG.error("Cannot finish bulk", e);
	    success = false;
	}
    }

    /**
     * Tests whether the bulk is ready (only beacuse of internal errors).
     * @param operation
     * @return 
     */
    public boolean isBulkReady(String operation) {
	boolean ready = bulksStack > 0;

	if (!ready) {
	    Exception e = new RuntimeException("Cannot perform " + operation + ", bulk not ready");
	    LOG.error("Bulk not ready", e);
	}

	return ready;
    }

    /**
     * Returns where during the bulk operations occured some error.
     * @return 
     */
    public boolean isSuccessfull() {
	return success;
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="insert, update, delete">
    /**
     * Inserts entity. Bulk must be ready.
     * @param <T>
     * @param item
     * @return 
     */
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

    /**
     * Updates entity. Bulk must be ready.
     * @param <T>
     * @param item
     * @return 
     */
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

    /**
     * Removes entity. Bulk must be ready.
     * @param <T>
     * @param item
     * @return 
     */
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

    /**
     * Inserts entity, automatically starts and finishes bulk.
     * @param <T>
     * @param item
     * @return 
     */
    public <T> boolean insertSingle(T item) {
	startBulk();
	insert(item);
	finishBulk();
	return success;
    }

    /**
     * Updates entity, automatically starts and finishes bulk.
     * @param <T>
     * @param item
     * @return 
     */
    public <T> boolean updateSingle(T item) {
	startBulk();
	update(item);
	finishBulk();
	return success;
    }

    /**
     * Removes entity, automatically starts and finishes bulk.
     * @param <T>
     * @param item
     * @return 
     */
    public <T> boolean removeSingle(T item) {
	startBulk();
	remove(item);
	finishBulk();
	return success;
    }
//</editor-fold>

    /**
     * Runs validation of given entity. Logs.
     * 
     * @see https://coderanch.com/t/589060/java/bean-validation-error-JPA-persist
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
