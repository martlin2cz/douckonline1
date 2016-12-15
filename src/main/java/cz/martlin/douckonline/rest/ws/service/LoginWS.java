package cz.martlin.douckonline.rest.ws.service;

import cz.martlin.douckonline.business.model.base.User;
import cz.martlin.douckonline.rest.ws.tools.WSAuthorisation;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Web service performing logging in (authorisation).
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
@Path("login")
public class LoginWS {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final WSAuthorisation auth = new WSAuthorisation();

    //@Context
    //private UriInfo context;
    public LoginWS() {
    }

    /**
     * Checks username and password and responds auth token. If credentials are
     * invalid, responds 401 status code and empty string.
     *
     * @param username
     * @param password
     * @return
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/get-auth-token")
    public String getAuthToken(
	    @QueryParam("username") String username,
	    @QueryParam("password") String password) {
	LOG.debug("Attempt to log in using password: " + username + ", " + password);

	User user = auth.tryAuthoriseByLogin(username, password, null);

	String authToken = auth.computeAuthToken(user);
	return authToken;

    }

    /**
     * Checks username and auth code. Returns type name ("lector", "student" or
     * "manager"). If credentials are invalid, responds 401 status code and
     * empty string.
     *
     * @param username
     * @param authToken
     * @return
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/check-auth-token")
    public String checkAuthToken(
	    @QueryParam("username") String username,
	    @QueryParam("auth-token") String authToken) {
	LOG.debug("Attempt to log in using authToken: " + username + ", " + authToken);

	User user = auth.tryAuthoriseByAuthToken(username, authToken, null);
	return user.getClass().getSimpleName();
    }
}
