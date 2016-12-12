package cz.martlin.douckonline.business.tools;

import cz.martlin.douckonline.business.model.lector.Certificate;
import cz.martlin.douckonline.business.model.lector.Education;
import cz.martlin.douckonline.business.model.lector.Lector;
import cz.martlin.douckonline.business.model.lector.SubjTeachingSpec;
import cz.martlin.douckonline.business.model.managment.Payment;
import cz.martlin.douckonline.business.model.managment.RequestReaction;
import cz.martlin.douckonline.business.model.managment.TeachingRequest;
import cz.martlin.douckonline.business.model.teaching.Lesson;
import cz.martlin.douckonline.business.model.teaching.Student;
import cz.martlin.douckonline.business.model.teaching.Subject;
import cz.martlin.douckonline.business.model.teaching.Teaching;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.eclipse.persistence.exceptions.DatabaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
public class DbAccessor {

    private final static Logger LOG = LoggerFactory.getLogger(DbAccessor.class);
    private final static String PERSISTENCE_UNIT_NAME = "pu_douckonline_1";

    private static final DbAccessor INSTANCE = new DbAccessor();

    private static EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

//<editor-fold defaultstate="collapsed" desc="singleton">
    private DbAccessor() {

    }

    public static DbAccessor get() {
	return INSTANCE;
    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="start and finish of whole db mechanism">
    public static void createFactory() {
	LOG.debug("Starting entity manager factory");
	entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    public static void closeFactory() {
	LOG.debug("Closing entity manager factory");
	entityManagerFactory.close();
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="loading of list">
    public <T> T getById(Class<T> clazz, Object identificator) {
	EntityManager entityManager = entityManagerFactory.createEntityManager();
	T item = entityManager.find(clazz, identificator);
	entityManager.close();
	return item;
    }

    /**
     * Lists all entities of given class.
     *
     * @param <T>
     * @param clazz
     * @return
     */
    public <T> List<T> listAll(Class<T> clazz) {
	SimpleQuery<T> simple = createQuery(clazz);
	Query jpa = toJPAquery(simple, null, null);

	List<T> list = jpa.getResultList();
	return list;
    }

    /**
     * Lists all entities of given class matching given condition
     * (<code>attr[i] = values[i]</code>).
     *
     * @param <T>
     * @param clazz
     * @param attrs
     * @param values
     * @return
     */
    public <T> List<T> listByCond(Class<T> clazz, String attrs[], Object values[]) {
	SimpleQuery<T> simple = createQuery(clazz);
	for (String attr : attrs) {
	    simple.addWhereAttribute(attr);
	}

	Query jpa = toJPAquery(simple, attrs, values);
	List<T> list = jpa.getResultList();
	return list;
    }

    /**
     * Lists by given condition.
     *
     * @param <T>
     * @param clazz
     * @param isStar
     * @param classes
     * @param attrs
     * @param vars
     * @param values
     * @return
     */
    public <T> List<T> listByCond(Class<T> clazz, boolean isStar, Class<?>[] classes, String attrs[], String[] vars, Object values[]) {
	SimpleQuery<T> simple = createQuery(clazz, isStar, classes);
	for (int i = 0; i < attrs.length; i++) {
	    simple.addWhereVariable(attrs[i], false, vars[i]);
	}

	return runSimpleQuery(simple, vars, values);
    }
//</editor-fold>

    /**
     *
     * @param jpql
     * @param vars
     * @param vals
     * @return
     */
    public <T> T runNativeJPQL(String jpql, String[] vars, Object[] vals) {
	Query query = toJPAQuery(jpql, vars, vals);
	Object result = query.getSingleResult();
	return (T) result;
    }

//<editor-fold defaultstate="collapsed" desc="bulk of modifications start and finish">
    public boolean startBulkModification() {
	try {
	    entityManager = entityManagerFactory.createEntityManager();
	    entityManager.getTransaction().begin();
	    return true;
	} catch (Exception e) {
	    LOG.error("Cannot start bulk modifications", e);
	    entityManager = null;
	    return false;
	}
    }

    public boolean finishBulkModification() {
	try {
	    entityManager.getTransaction().commit();
	    entityManager = null;
	    return true;
	} catch (Exception e) {
	    LOG.error("Cannot finish bulk modifications", e);
	    entityManager = null;
	    return false;
	}
    }

    public boolean isBulkModificationReady(String operation) {
	boolean ready = (entityManager != null);

	if (!ready) {
	    LOG.error("Cannot perform " + operation + ", bulk modification not ready");
	}

	return ready;
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="insert, update, delete">
    
    
    public <T> boolean insert(T item) {
	if (!isBulkModificationReady("insert")) {
	    return false;
	}

	try {
	    validate(item);
	    entityManager.persist(item);
	    return true;
	} catch (Exception e) {
	    LOG.error("Cannot insert " + item, e);
	    return false;
	}
    }
    
    public <T> boolean update(T item) {
	if (!isBulkModificationReady("update")) {
	    return false;
	}

	try {
	    validate(item);
	    entityManager.merge(item);	//TODO returnval?
	    return true;
	} catch (Exception e) {
	    LOG.error("Cannot update " + item, e);
	    return false;
	}

    }

    public <T> boolean remove(T item) {
	if (!isBulkModificationReady("remove")) {
	    return false;
	}

	try {
	    entityManager.remove(item);
	    return true;
	} catch (Exception e) {
	    LOG.error("Cannot remove " + item, e);
	    return false;
	}
    }

    
    //TODO FIXME: return start && insert && finish? or how to handle errors?
    
    public <T> boolean insertSingle(T item) {
	startBulkModification();
	insert(item);
	return finishBulkModification();
    }
    
    public <T> boolean updateSingle(T item) {
	startBulkModification();
	update(item);
	return finishBulkModification();
    }
    
    public <T> boolean removeSingle(T item) {
	startBulkModification();
	remove(item);
	return finishBulkModification();
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

//<editor-fold defaultstate="collapsed" desc="helping methods (creating of queries)">
    public <T> SimpleQuery<T> createQuery(Class<T> clazz) {
	return new SimpleQuery<>(clazz);
    }

    public <T> SimpleQuery<T> createQuery(Class<T> clazz, boolean isStar, Class<?>... from) {
	return new SimpleQuery<>(relations(), isStar, clazz, from);
    }

    private <T> Query toJPAquery(SimpleQuery<T> query, String[] variables, Object[] values) {
	String jpql = query.toJPQL();
	return toJPAQuery(jpql, variables, values);
    }

    private Query toJPAQuery(String jpql, String[] variables, Object[] values) {
	EntityManager entityManager = entityManagerFactory.createEntityManager();
	Query query = entityManager.createQuery(jpql);

	if (variables != null) {
	    for (int i = 0; i < values.length; i++) {
		query.setParameter(variables[i], values[i]);
	    }
	}

	return query;
    }

    private <T> List<T> runSimpleQuery(SimpleQuery<T> simple, String[] attrs, Object[] values) {
	LOG.debug("Running JPQL: " + simple.toJPQL() + " with " + Arrays.toString(attrs));
	Query jpa = toJPAquery(simple, attrs, values);
	List<T> list = jpa.getResultList();
	return list;
    }

    private static Relations relations() {
	Relations relations = new Relations();

	relations.addRelation(//
		SubjTeachingSpec.class, Lector.class, "lector");
	relations.addRelation(//
		SubjTeachingSpec.class, Subject.class, "subject");

	relations.addRelation(//
		Payment.class, Student.class, "student");

	relations.addRelation(//
		Teaching.class, Student.class, "student");
	relations.addRelation(//
		Teaching.class, Lector.class, "lector");
	relations.addRelation(//
		Teaching.class, Subject.class, "subject");

	relations.addRelation(//
		Lesson.class, Teaching.class, "teaching");

	relations.addRelation(//
		TeachingRequest.class, Subject.class, "subject");
	relations.addRelation(//
		RequestReaction.class, TeachingRequest.class, "request");
	relations.addRelation(//
		RequestReaction.class, Lector.class, "lector");

	/*
	relations.addRelation(//
		Certificate.class, Lector.class, "lector");
	relations.addRelation(//
		Certificate.class, Lector.class, "lector");
	 */
	return relations;
    }

//</editor-fold>
}
