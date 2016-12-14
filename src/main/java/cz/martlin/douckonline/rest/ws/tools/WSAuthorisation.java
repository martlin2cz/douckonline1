package cz.martlin.douckonline.rest.ws.tools;

import cz.martlin.douckonline.business.logic.Users;
import cz.martlin.douckonline.business.model.base.User;
import java.security.MessageDigest;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.core.Response;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
public class WSAuthorisation {

    private static final String ALGORITHM = "MD5";

    private final Users USERS = new Users();

    public WSAuthorisation() {
    }

//<editor-fold defaultstate="collapsed" desc="main public api">
    public <T extends User> T tryAuthoriseByLogin(String username, String password, Class<T> clazz) {
	boolean success = USERS.isValid(username, password);
	
	if (success) {
	    T user = tryFindUser(username, clazz);
	    
	    if (user != null) {
		return user;
	    }
	}
	
	throw new NotAuthorizedException("authorise by login");
    }
    
    public <T extends User> T tryAuthoriseByAuthToken(String username, String authToken, Class<T> clazz) {
	T user = tryFindUser(username, clazz);
	
	if (user != null) {
	    String computedToken = computeAuthToken(user);
	    
	    if (computedToken.equals(authToken)) {
		return user;
	    }
	}
	
	throw new NotAuthorizedException("authorise by auth token");
    }
    
    
    public String computeAuthToken(User user) {
	String value = user.getLoginName() + user.getPasswordHash();
	try {
	    MessageDigest md = MessageDigest.getInstance(ALGORITHM);
	    
	    byte[] valueBytes = value.getBytes();
	    byte[] hashBytes = md.digest(valueBytes);
	    String hashStr = DatatypeConverter.printBase64Binary(hashBytes);
	    
	    return hashStr;
	} catch (Exception ex) {
	    throw new RuntimeException("Cannot generate auth token", ex);
	}
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="utilities">
    
    private <T> T tryFindUser(String username, Class<T> clazz) {
	User user = USERS.findUser(username);
	if (user == null) {
	    return null;
	}
	
	if (clazz != null) {
	    if (!user.getClass().equals(clazz)) {
		return null;
	    }
	}
	
	T casted = (T) user;
	return casted;
    }
    
//</editor-fold>
}
