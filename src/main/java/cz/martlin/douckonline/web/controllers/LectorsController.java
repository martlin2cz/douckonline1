/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.martlin.douckonline.web.controllers;

import cz.martlin.douckonline.business.data.Lectors;
import cz.martlin.douckonline.business.model.Lector;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
@RequestScoped
@Named("lectorsController")
public class LectorsController {
    
    private final Lectors lectors = new Lectors();

    private String newLectorName;
    
    public LectorsController() {
    }
    
    public List<Lector> getLectors() {
	return lectors.listAllLectors();
    }

    public String getNewLectorName() {
	return newLectorName;
    }

    public void setNewLectorName(String newLectorName) {
	this.newLectorName = newLectorName;
    }
    
    public void addLector() {
	lectors.addLector(new Lector(newLectorName));
    }
    
    
    
}
