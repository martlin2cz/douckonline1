package cz.martlin.douckonline.rest.ws.service;

import cz.martlin.douckonline.business.model.managment.Manager;
import cz.martlin.douckonline.rest.ws.tools.WSAuthorisation;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * Web service for manager.
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
@Path("manager")
public class ManagerWS {

    private final WSAuthorisation auth = new WSAuthorisation();

    public ManagerWS() {
    }

    /**
     * Returns managers profile.
     *
     * @param username
     * @param authToken
     * @return
     */
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
