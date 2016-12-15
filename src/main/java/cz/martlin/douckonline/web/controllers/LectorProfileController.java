package cz.martlin.douckonline.web.controllers;

import cz.martlin.douckonline.business.logic.Requests;
import cz.martlin.douckonline.business.logic.Teachings;
import cz.martlin.douckonline.business.model.lector.Lector;
import cz.martlin.douckonline.business.model.managment.TeachingRequest;
import cz.martlin.douckonline.business.model.teaching.Lesson;
import cz.martlin.douckonline.business.model.teaching.Teaching;
import cz.martlin.douckonline.web.rest.LoginSession;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
@RequestScoped
@Named("lectorsProfileController")
public class LectorProfileController {

    private static final int SHOW_LESSONS_DAYS_AGO = 30;
    private final Teachings teachings = new Teachings();
    private final Requests requests = new Requests();

    @Inject
    private LoginSession session;

    public LectorProfileController() {
    }

    public Lector getLoggedLector() {
	return session.getLoggedLector();
    }

    public List<TeachingRequest> getCurrentRequests() {
	return requests.listPendingForLector(getLoggedLector());
    }

    public List<Teaching> getCurrentTeachings() {
	return teachings.listTeachingsOfLector(getLoggedLector());
    }

    public List<Lesson> getActualLessons() {
	return teachings.getLessonsOf(getLoggedLector(), SHOW_LESSONS_DAYS_AGO);
    }

}
