package cz.martlin.douckonline.business.tools;

import cz.martlin.douckonline.business.model.lector.Certificate;
import cz.martlin.douckonline.business.model.lector.Education;
import cz.martlin.douckonline.business.model.lector.Lector;
import cz.martlin.douckonline.business.model.lector.SubjTeachingSpec;
import cz.martlin.douckonline.business.model.managment.Payment;
import cz.martlin.douckonline.business.model.teaching.Lesson;
import cz.martlin.douckonline.business.model.teaching.Student;
import cz.martlin.douckonline.business.model.teaching.Subject;
import cz.martlin.douckonline.business.model.teaching.Teaching;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
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

    private static EntityManagerFactory entityManagerFactory;

    public DbAccessor() {
    }

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
//<editor-fold defaultstate="collapsed" desc="insert, update, delete">
    public <T> boolean insert(T item) {
	return save(item, "insert");
    }

    public <T> boolean update(T item) {
	return save(item, "update");
    }

    public <T> boolean remove(T item) {
	try {
	    EntityManager entityManager = entityManagerFactory.createEntityManager();
	    entityManager.getTransaction().begin();
	    entityManager.remove(item);
	    entityManager.getTransaction().commit();
	    return true;
	} catch (Exception e) {
	    LOG.error("Cannot remove " + item, e);
	    return false;
	}
    }

    private <T> boolean save(T item, String operation) {
	try {
	    EntityManager entityManager = entityManagerFactory.createEntityManager();
	    entityManager.getTransaction().begin();
	    entityManager.persist(item);
	    entityManager.getTransaction().commit();
	    return true;
	} catch (Exception e) {
	    LOG.error("Cannot " + operation + " " + item, e);
	    return false;
	}
    }
//</editor-fold>

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
