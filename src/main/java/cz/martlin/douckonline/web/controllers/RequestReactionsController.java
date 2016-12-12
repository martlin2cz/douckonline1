package cz.martlin.douckonline.web.controllers;

import cz.martlin.douckonline.business.logic.Requests;
import cz.martlin.douckonline.business.logic.Teachings;
import cz.martlin.douckonline.business.model.managment.RequestReaction;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
@RequestScoped
@Named("requestReactionsController")
public class RequestReactionsController {
    
    private final Requests requests = new Requests();
    
    public void assignToLector(RequestReaction reaction) {
	 requests.reactionToTeaching(reaction);
    }
    
    
    public void assignSomething(){
	System.out.println("Asssssigning, or something");
    }
}
