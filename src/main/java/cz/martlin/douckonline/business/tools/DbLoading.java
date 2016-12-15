package cz.martlin.douckonline.business.tools;

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
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
public class DbLoading {

    private final static Logger LOG = LoggerFactory.getLogger(DbLoading.class);

    private static final DbLoading INSTANCE = new DbLoading();

    private final DatabaseAccess db = DatabaseAccess.get();

//<editor-fold defaultstate="collapsed" desc="singleton">
    private DbLoading() {

    }

    public static DbLoading get() {
	return INSTANCE;
    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="loading of list">
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

//<editor-fold defaultstate="collapsed" desc="other loadings">
    public <T> T getById(Class<T> clazz, Object identificator) {
	T item = db.getEntityManager().find(clazz, identificator);
	return item;
    }

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
	Query query = db.getEntityManager().createQuery(jpql);

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
