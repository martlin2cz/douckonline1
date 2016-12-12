/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.martlin.douckonline.business.logic;

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

    private final DbAccessor db = DbAccessor.get();

    public Managers() {
    }
  
//<editor-fold defaultstate="collapsed" desc="load managers">
    
    /**
     * Lists all managers.
     * @return
     */
    public List<Manager> listAllManagers() {
	LOG.debug("Loading managers");
	List<Manager> managers = db.listAll(Manager.class);
	return managers;
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="add, update manager">
    
    /**
     * Adds specified manager with given password.
     * @param manager
     * @param password
     * @return
     */
    public boolean addManager(Manager manager, String password) {
	LOG.debug("Adding manager");
	
	final Users users = new Users();
	return users.registerUser(manager, password);
    }
    
    /**
     * Updates specified manager.
     * @param manager
     * @return
     */
    public boolean updateManager(Manager manager) {
	LOG.debug("Updating manager");
	
	return db.update(manager);
    }
//</editor-fold>
}
