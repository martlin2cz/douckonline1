/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.martlin.douckonline.business.data;

import cz.martlin.douckonline.business.model.lector.Lector;
import cz.martlin.douckonline.business.model.managment.Manager;
import cz.martlin.douckonline.business.tools.DbAccessor;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
public class Managers {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final DbAccessor db = new DbAccessor();

    public Managers() {
    }
  
    
    
    public List<Manager> listAllManagers() {
	LOG.debug("Loading managers");
	List<Manager> managers = db.listAll(Manager.class);
	return managers;
    }
    
    
    public boolean addManager(Manager manager) {
	LOG.debug("Adding manager");
	return db.insert(manager);
    }
}
