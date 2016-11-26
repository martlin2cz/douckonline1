package cz.martlin.douckonline.web.rest;

import cz.martlin.douckonline.business.tools.DbAccessor;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Based on: 
 * http://stackoverflow.com/a/7862798/3797793, 
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
	DbAccessor.createFactory();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
	DbAccessor.closeFactory();
    }
    
}
