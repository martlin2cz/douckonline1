package cz.martlin.douckonline.web.controllers;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
@RequestScoped
@Named("miscController")
public class MiscController {

    public MiscController() {
    }

    public void doNothing() {
	//really nothing
    }
}
