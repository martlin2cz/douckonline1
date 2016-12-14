package cz.martlin.douckonline.rest.ws.service;

import cz.martlin.douckonline.business.logic.Moneys;
import cz.martlin.douckonline.business.logic.Teachings;
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
public class StudentWS {

    private final Teachings teachings = new Teachings();
    private final Moneys moneys = new Moneys();
    
    private final WSAuthorisation auth = new WSAuthorisation();

    public StudentWS() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/profile")
    public Student getProfile(
	    @QueryParam("username") String username,
	    @QueryParam("auth-token") String authToken) {

	Student student = auth.tryAuthoriseByAuthToken(username, authToken, Student.class);
	return student;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/balance")
    public BigDecimal getBalance(
	    @QueryParam("username") String username,
	    @QueryParam("auth-token") String authToken) {

	Student student = auth.tryAuthoriseByAuthToken(username, authToken, Student.class);

	return moneys.getBallanceOfStudent(student);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/lessons")
    public List<Lesson> getLessons(
	    @QueryParam("username") String username,
	    @QueryParam("auth-token") String authToken) {

	Student student = auth.tryAuthoriseByAuthToken(username, authToken, Student.class);

	return teachings.getLessonsOf(student, null);
    }
}
