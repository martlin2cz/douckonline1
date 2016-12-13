package cz.martlin.douckonline.web.controllers;

import cz.martlin.douckonline.business.logic.Requests;
import cz.martlin.douckonline.business.logic.Teachings;
import cz.martlin.douckonline.business.model.lector.Lector;
import cz.martlin.douckonline.business.model.managment.RequestReaction;
import cz.martlin.douckonline.business.model.managment.RequestReactionStatus;
import cz.martlin.douckonline.business.model.managment.TeachingRequest;
import cz.martlin.douckonline.business.model.managment.TeachingRequest_;
import cz.martlin.douckonline.web.rest.LoginSession;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
@RequestScoped
@Named("requestReactionsController")
public class RequestReactionsController {
    
    private final Requests requests = new Requests();
    @Inject LoginSession login;

    private RequestReactionStatus status;
    private String description;
    
    public RequestReactionsController() {
    }
//<editor-fold defaultstate="collapsed" desc="getters and setters">
    
    public RequestReactionStatus getStatus() {
	return status;
    }
    
    public void setStatus(RequestReactionStatus status) {
	this.status = status;
    }
    
    public String getDescription() {
	return description;
    }
    
    public void setDescription(String description) {
	this.description = description;
    }
    
    public RequestReactionStatus[] getAllStatuses() {
	return RequestReactionStatus.values();
    }
    
//</editor-fold>
    
    
    //<editor-fold defaultstate="collapsed" desc="action methods">
    
    public void assignToLector(RequestReaction reaction) {
	requests.reactionToTeaching(reaction);
    }
    
    public void saveReaction(TeachingRequest request) {
	Lector lector = login.getLoggedLector();
	requests.react(request, lector, status, description);
    }
    
//</editor-fold>
}
