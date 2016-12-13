package cz.martlin.douckonline.web.controllers;

import cz.martlin.douckonline.business.logic.Requests;
import cz.martlin.douckonline.business.model.lector.Lector;
import cz.martlin.douckonline.business.model.managment.TeachingRequest;
import cz.martlin.douckonline.web.utils.JSFTools;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
@RequestScoped
@Named("assignRequestController")
public class AssignRequestController {
    
    private final Requests REQUESTS = new Requests();
    
    private Lector lector;
    private int cost;
    private String description;
    

    public AssignRequestController() {
    }
    
    @PostConstruct
    public void init() {
	
    }
//<editor-fold defaultstate="collapsed" desc="getters and setters">
    
    public Lector getLector() {
	return lector;
    }
    
    public void setLector(Lector lector) {
	this.lector = lector;
    }
    
    public int getCost() {
	return cost;
    }

    public void setCost(int cost) {
	this.cost = cost;
    }
    
    public String getDescription() {
	return description;
    }
    
    public void setDescription(String description) {
	this.description = description;
    }

    
    
//</editor-fold>
    
    public void assign(TeachingRequest request) {
	
	
	REQUESTS.requestToTeaching(request, lector, description, cost);
    }
    
    
}
