package cz.martlin.douckonline.rest.ws.service;

import cz.martlin.douckonline.business.logic.Requests;
import cz.martlin.douckonline.business.logic.Teachings;
import cz.martlin.douckonline.business.model.lector.Lector;
import cz.martlin.douckonline.business.model.managment.TeachingRequest;
import cz.martlin.douckonline.business.model.teaching.Lesson;
import cz.martlin.douckonline.business.model.teaching.Teaching;
import cz.martlin.douckonline.rest.ws.tools.WSAuthorisation;
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
@Path("lector")
public class LectorWS {

    private final Requests requests = new Requests();
    private final Teachings teachings = new Teachings();
    
    private final WSAuthorisation auth = new WSAuthorisation();

    public LectorWS() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/profile")
    public Lector getProfile(
	    @QueryParam("username") String username,
	    @QueryParam("auth-token") String authToken) {

	Lector lector = auth.tryAuthoriseByAuthToken(username, authToken, Lector.class);
	return lector;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/requests")
    public List<TeachingRequest> getRequests(
	    @QueryParam("username") String username,
	    @QueryParam("auth-token") String authToken) {

	Lector lector = auth.tryAuthoriseByAuthToken(username, authToken, Lector.class);
	return requests.listPendingForLector(lector);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/teachings")
    public List<Teaching> getTeachings(
	    @QueryParam("username") String username,
	    @QueryParam("auth-token") String authToken) {

	Lector lector = auth.tryAuthoriseByAuthToken(username, authToken, Lector.class);
	return teachings.listTeachingsOfLector(lector);
    }
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/lessons")
    public List<Lesson> getLessons(
	    @QueryParam("username") String username,
	    @QueryParam("auth-token") String authToken) {

	Lector lector = auth.tryAuthoriseByAuthToken(username, authToken, Lector.class);
	return teachings.getLessonsOf(lector, null);
    }
}
