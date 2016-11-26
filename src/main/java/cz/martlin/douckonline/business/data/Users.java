/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.martlin.douckonline.business.data;

import cz.martlin.douckonline.business.model.lector.Lector;
import cz.martlin.douckonline.business.model.managment.Manager;
import cz.martlin.douckonline.business.model.teaching.Student;
import cz.martlin.douckonline.business.model.managment.User;
import cz.martlin.douckonline.business.tools.DbAccessor;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
public class Users {
    private final Students students = new Students();
    private final Lectors lectors = new Lectors();
    private final Managers managers = new Managers();
    
    public Users() {
    }
    
    public List<User> listAllUsers() {
	List<User> users = new ArrayList<>();
	
	for (Student student: students.listAllStudents()) {
	    users.add(student);
	}
	for (Lector lector: lectors.listAllLectors()) {
	    users.add(lector);
	}
	for (Manager manager: managers.listAllManagers()) {
	    users.add(manager);
	}
	
	return users;
	
    }

    public User findUser(String loginName) {
	for (User user: listAllUsers()) {
	    if (user.getLoginName().equals(loginName)) {
		return user;
	    }
	}
	
	return null;
    }


    
}

