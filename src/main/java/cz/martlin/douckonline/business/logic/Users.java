package cz.martlin.douckonline.business.logic;

import cz.martlin.douckonline.business.model.lector.Lector;
import cz.martlin.douckonline.business.model.managment.Manager;
import cz.martlin.douckonline.business.model.teaching.Student;
import cz.martlin.douckonline.business.model.base.User;
import cz.martlin.douckonline.business.tools.DbLoading;
import cz.martlin.douckonline.business.tools.DbModifying;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implements logic on users level.
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
public class Users {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final Students students = new Students();
    private final Lectors lectors = new Lectors();
    private final Managers managers = new Managers();
    private final Passwording passwording = new Passwording();

    private final DbModifying dbm = DbModifying.get();

    public Users() {
    }

//<editor-fold defaultstate="collapsed" desc="listing, finding and loading users">
    /**
     * Lists all users. Warning, not effective.
     *
     * @return
     */
    public List<User> listAllUsers() {
	LOG.trace("Listing all users");

	List<User> users = new ArrayList<>();

	for (Student student : students.listAllStudents()) {
	    users.add(student);
	}
	for (Lector lector : lectors.listAllLectors()) {
	    users.add(lector);
	}
	for (Manager manager : managers.listAllManagers()) {
	    users.add(manager);
	}

	return users;

    }

    /**
     * Finds user by its loginName. Extremelly uneffective!
     *
     * @param loginName
     * @return
     */
    public User findUser(String loginName) {
	LOG.trace("Finding user by loginName");

	//TODO FIXME optimize me! use Map<loginName, User> ?
	for (User user : listAllUsers()) {
	    if (user.getLoginName().equals(loginName)) {
		return user;
	    }
	}

	return null;
    }

    /**
     * Counts number of users of given name (to make loginName unique).
     *
     * @param fullName
     * @return
     */
    public int countUsersOfName(String fullName) {
	LOG.trace("Counting users of name");
	LOG.warn("not implemented, returning 0");

	//TODO
	return 0;
    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="create, update, modify user">
    /**
     * Registers user.
     *
     * @param user
     * @param password
     * @return
     */
    public boolean registerUser(User user, String password) {
	LOG.trace("Registering user");

	Calendar registeredAt = Calendar.getInstance();
	String passwordSalt = passwording.generateSalt();
	String passwordHash = passwording.hashPassword(password, passwordSalt);
	String loginName = createLoginName(user.getReallName());

	user.setRegisteredAt(registeredAt);
	user.setPasswordSalt(passwordSalt);
	user.setPasswordHash(passwordHash);
	user.setLoginName(loginName);

	return dbm.insertSingle(user);
    }

    /**
     * Updates user's password.
     *
     * @param user
     * @param password
     * @return
     */
    public boolean changePassword(User user, String password) {
	LOG.trace("Changing password");

	String passwordSalt = passwording.generateSalt();
	String passwordHash = passwording.hashPassword(password, passwordSalt);

	user.setPasswordSalt(passwordSalt);
	user.setPasswordHash(passwordHash);

	return dbm.update(user);
    }

    /**
     * Makes user logged in just now (sets its lastLoginAt to now).
     *
     * @param user
     * @return
     */
    public boolean userLoggedIn(User user) {	//TODO use me!
	LOG.trace("User logged in");

	Calendar lastLoginAt = Calendar.getInstance();
	user.setLastLoginAt(lastLoginAt);

	return dbm.update(user);
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="misc">
    /**
     * Is user specified by given credentals valid loginable user?
     *
     * @param username
     * @param password
     * @return
     */
    public boolean isValid(String username, String password) {
	User user = findUser(username);
	if (user == null) {
	    return false;
	}

	String passwordSalt = user.getPasswordSalt();
	String passwordHash = passwording.hashPassword(password, passwordSalt);

	return user.getPasswordHash().equals(passwordHash);
    }

    /**
     * Converts user's real name to loginName (remove whitespaces and - if
     * needed add unique number).
     *
     * @param realName
     * @return
     */
    private String createLoginName(String realName) {
	String name = realName.replace(" ", "");
	String loginName = name;

	int count = countUsersOfName(realName);
	if (count > 0) {
	    loginName += Integer.toString(count);
	}

	return loginName;
    }
//</editor-fold>

}
