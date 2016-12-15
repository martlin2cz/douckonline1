package cz.martlin.douckonline.rest.ws.tools;

import cz.martlin.douckonline.business.logic.Users;
import cz.martlin.douckonline.business.model.base.User;
import java.security.MessageDigest;
import javax.ws.rs.NotAuthorizedException;
import javax.xml.bind.DatatypeConverter;

/**
 * Class implementing authorisation to web service.
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
public class WSAuthorisation {

    private static final String ALGORITHM = "MD5";

    private final Users USERS = new Users();

    public WSAuthorisation() {
    }

//<editor-fold defaultstate="collapsed" desc="main public api">
    /**
     * Tries to authorise using username and password. Returns instance of given
     * user's child's class (or User if null). If fails, throws exception.
     *
     * @param <T>
     * @param username
     * @param password
     * @param clazz
     * @return
     */
    public <T extends User> T tryAuthoriseByLogin(String username, String password, Class<T> clazz) throws NotAuthorizedException {
	boolean success = USERS.isValid(username, password);

	if (success) {
	    T user = tryFindUser(username, clazz);

	    if (user != null) {
		return user;
	    }
	}

	throw new NotAuthorizedException("authorise by login");
    }

    /**
     * Tries to authorise user using the auth token. Returns required user or
     * throws exception if not one found.
     *
     * @param <T>
     * @param username
     * @param authToken
     * @param clazz
     * @return
     */
    public <T extends User> T tryAuthoriseByAuthToken(String username, String authToken, Class<T> clazz) throws NotAuthorizedException {
	T user = tryFindUser(username, clazz);

	if (user != null) {
	    String computedToken = computeAuthToken(user);

	    if (computedToken.equals(authToken)) {
		return user;
	    }
	}

	throw new NotAuthorizedException("authorise by auth token");
    }

    /**
     * Computes auth token for given user.
     *
     * @param user
     * @return
     */
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
    /**
     * Tries to find user (with not authorisation check, just login and class).
     *
     * @param <T>
     * @param username
     * @param clazz
     * @return
     */
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
