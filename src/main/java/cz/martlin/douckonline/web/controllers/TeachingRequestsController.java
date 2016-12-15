package cz.martlin.douckonline.web.controllers;

import cz.martlin.douckonline.business.logic.Requests;
import cz.martlin.douckonline.business.model.managment.RequestReaction;
import cz.martlin.douckonline.business.model.managment.TeachingRequest;
import cz.martlin.douckonline.business.model.teaching.Level;
import cz.martlin.douckonline.web.utils.JSFTools;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import javax.inject.Named;

/**
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
@ViewScoped
@ManagedBean(name = "teachingRequestsController")
public class TeachingRequestsController implements Serializable {
    
    private final Requests REQUESTS = new Requests();
    
    private List<RequestReaction> currentReactions;
    private TeachingRequest currentRequest;
    
    public TeachingRequestsController() {
	currentRequest = new TeachingRequest();
    }

//<editor-fold defaultstate="collapsed" desc="getters and setters">
    public List<RequestReaction> getCurrentReactions() {
	return currentReactions;
    }
    
    public TeachingRequest getCurrentRequest() {
	return currentRequest;
    }
    
    public Level[] getAllLevels() {
	return Level.values();
    }
//</editor-fold>
        
    
//<editor-fold defaultstate="collapsed" desc="action methods">
    
    
    public void openNewRequest() {
	//in fact does nothing
    }
    
    public void openRequest(TeachingRequest request) {
	currentRequest = request;
    }
    
    public void saveRequest(TeachingRequest request) {
	boolean success;
	if (request.isPersisted()) {
	    throw new UnsupportedOperationException("Update of request");
	} else {
	    success = REQUESTS.addRequest(request);
	}
	
	JSFTools.savedOrFailed(success, "Request saved", "Request save failed");
    }
    
    public void loadReactions(TeachingRequest request) {
	currentReactions = request.getReactions();
    }
    
    
//</editor-fold>

    
    
    
}
