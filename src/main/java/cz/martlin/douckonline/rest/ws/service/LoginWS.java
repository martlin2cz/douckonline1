/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 *
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
@Path("login")
public class LoginWS {

    private final WSAuthorisation auth = new WSAuthorisation();

    @Context
    private UriInfo context;

    public LoginWS() {
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/get-auth-token")
    public String getAuthToken(
	    @QueryParam("username") String username,
	    @QueryParam("password") String password) {

	User user = auth.tryAuthoriseByLogin(username, password, null);

	String authToken = auth.computeAuthToken(user);
	return authToken;

    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/check-auth-token")
    public String checkAuthToken(
	    @QueryParam("username") String username,
	    @QueryParam("auth-token") String authToken) {

	User user = auth.tryAuthoriseByAuthToken(username, authToken, null);
	return user.getLoginName();
    }
}
