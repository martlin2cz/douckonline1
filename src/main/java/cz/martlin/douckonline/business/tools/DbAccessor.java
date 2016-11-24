/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.martlin.douckonline.business.tools;

import cz.martlin.douckonline.business.model.lector.Lector;
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
     private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu_douckonline_1");
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();
    
    public <T> List<T> listAll(Class<T> clazz) {
	String qlString = "SELECT item FROM " + clazz.getSimpleName() + " item";
	Query query = entityManager.createQuery(qlString);
	List<T> list =  query.getResultList();
	return list;
    }
    
    
    public <T> T getById(Class<T> clazz, Object identificator) {
	T item = entityManager.find(clazz, identificator);
	return item;
    }

    public <T> boolean insert(T item) {
	try {
	    entityManager.getTransaction().begin();
	    entityManager.persist(item);
	    entityManager.getTransaction().commit();
	    return true;
	} catch (Exception e) {
	    //entityManager.getTransaction().rollback();
	    LOG.error("Cannot insert " + item, e);
	    return false;
	} 
    }
    
    
    
    
}
