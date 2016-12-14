package cz.martlin.douckonline.rest.ws.service;

import cz.martlin.douckonline.business.logic.Moneys;
import cz.martlin.douckonline.business.logic.Teachings;
import cz.martlin.douckonline.business.model.managment.Manager;
import cz.martlin.douckonline.business.model.teaching.Lesson;
import cz.martlin.douckonline.business.model.teaching.Student;
import cz.martlin.douckonline.rest.ws.tools.WSAuthorisation;
import java.math.BigDecimal;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
@Path("student")
public class ManagerWS {
    
    private final WSAuthorisation auth = new WSAuthorisation();

    public ManagerWS() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/profile")
    public Manager getProfile(
	    @QueryParam("username") String username,
	    @QueryParam("auth-token") String authToken) {

	Manager manager = auth.tryAuthoriseByAuthToken(username, authToken, Manager.class);
	return manager;
    }

    //TODO
}
