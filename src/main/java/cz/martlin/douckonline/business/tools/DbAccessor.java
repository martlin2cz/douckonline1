/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.martlin.douckonline.business.tools;

import cz.martlin.douckonline.business.model.lector.Lector;
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
     
    private static EntityManagerFactory entityManagerFactory;// = Persistence.createEntityManagerFactory("pu_douckonline_1");
    
    public static void createFactory() {
	LOG.debug("Starting entity manager factory");
	entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }
    
    public static void closeFactory() {
	LOG.debug("Closing entity manager factory");
	entityManagerFactory.close();
    }
    
    public <T> List<T> listAll(Class<T> clazz) {
	EntityManager entityManager = entityManagerFactory.createEntityManager();
	String qlString = "SELECT item FROM " + clazz.getSimpleName() + " item";
	Query query = entityManager.createQuery(qlString);
	List<T> list =  query.getResultList();
	entityManager.close();
	return list;
    }
    
    public <T> List<T> listBySimpleCond(Class<T> clazz, Class<?>[] froms, String attrs[], String vars[], Object values[]) {
	EntityManager entityManager = entityManagerFactory.createEntityManager();
	StringBuilder qlString = new StringBuilder();
	qlString.append("SELECT ");
	qlString.append(clazz.getSimpleName().toLowerCase());
	qlString.append(" FROM ");
	for (int i = 0; i < froms.length; i ++) {
	    Class<?> fromClazz = froms[i];
	    qlString.append(fromClazz.getSimpleName());
	    qlString.append(" ");
	    qlString.append(fromClazz.getSimpleName().toLowerCase());
	    
	    if ((i + 1) < froms.length) {
		qlString.append(", ");
	    }
	}
	
	qlString.append(" WHERE ");
	
	for (int i = 0; i < attrs.length; i++) {
	    qlString.append(attrs[i]);
	    qlString.append(" = :");
	    qlString.append(vars[i]);
	    if ((i + 1) < attrs.length) {
		qlString.append(" AND ");
	    }
	}
	
	Query query = entityManager.createQuery(qlString.toString());
	for (int i = 0; i < attrs.length; i++) {
	    query.setParameter(vars[i], values[i]);
	}
	
	List<T> list =  query.getResultList();
	entityManager.close();
	return list;
    }
    
    
    public <T> T getById(Class<T> clazz, Object identificator) {
	EntityManager entityManager = entityManagerFactory.createEntityManager();
	T item = entityManager.find(clazz, identificator);
	entityManager.close();
	return item;
    }

    public <T> boolean insert(T item) {
	try {
	    EntityManager entityManager = entityManagerFactory.createEntityManager();
	    entityManager.getTransaction().begin();
	    entityManager.persist(item);
	    entityManager.getTransaction().commit();
	    entityManager.close();
	    return true;
	} catch (Exception e) {
	    //entityManager.getTransaction().rollback();
	    LOG.error("Cannot insert " + item, e);
	    return false;
	} 
    }
    
    
    
    
}
