package cz.martlin.douckonline.web.rest;

import cz.martlin.douckonline.business.tools.DatabaseAccess;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 *
 * Servlet context listener performing correct startup and finish of database.
 *
 * @see http://stackoverflow.com/a/7862798/3797793,
 * @see
 * http://www.hhutzler.de/blog/jpa-best-practice-to-get-and-close-entitymanagerfactory-to-avoid-unknown-entity-bean-class-error/
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
@WebListener
public class EntityManagerContextListener implements ServletContextListener {

    public EntityManagerContextListener() {
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
	DatabaseAccess.startupDatabase();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
	DatabaseAccess.finishDatabase();
    }

}
