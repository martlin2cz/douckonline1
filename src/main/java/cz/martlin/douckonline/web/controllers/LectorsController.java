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

/**
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
@RequestScoped
@Named("lectorsController")
public class LectorsController {
    
    private final Lectors lectors = new Lectors();
    
    public List<Lector> getLectors() {
	return lectors.listAllLectors();
    }
    
}
