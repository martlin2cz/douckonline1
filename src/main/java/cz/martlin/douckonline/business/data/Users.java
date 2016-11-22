/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.martlin.douckonline.business.data;

import cz.martlin.douckonline.business.model.Lector;
import cz.martlin.douckonline.business.model.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
public class Users {
    private final Lectors lectors = new Lectors();

    public Users() {
    }
    
    public List<User> listAllUsers() {
	List<User> users = new ArrayList<>();
	
	for (Lector lector: lectors.listAllLectors()) {
	    users.add(lector);
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

