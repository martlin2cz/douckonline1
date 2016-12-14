/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.rest.application.config;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
	Set<Class<?>> resources = new java.util.HashSet<>();
	addRestResourceClasses(resources);
	return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
	resources.add(cz.martlin.douckonline.rest.ws.service.LectorFacadeREST.class);
	resources.add(cz.martlin.douckonline.rest.ws.service.LectorWS.class);
	resources.add(cz.martlin.douckonline.rest.ws.service.LessonFacadeREST.class);
	resources.add(cz.martlin.douckonline.rest.ws.service.LoginWS.class);
	resources.add(cz.martlin.douckonline.rest.ws.service.ManagerWS.class);
	resources.add(cz.martlin.douckonline.rest.ws.service.StudentFacadeREST.class);
	resources.add(cz.martlin.douckonline.rest.ws.service.StudentWS.class);
	resources.add(cz.martlin.douckonline.rest.ws.service.SubjectFacadeREST.class);
	resources.add(cz.martlin.douckonline.rest.ws.service.TeachingFacadeREST.class);
    }
    
}
