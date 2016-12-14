package cz.martlin.douckonline.business.logic;

import java.security.MessageDigest;
import java.util.Random;
import javax.xml.bind.DatatypeConverter;


/**
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
public class Passwording {
    private static final String ALGORITHM = "MD5";
    
    private final Random random = new Random();

    public Passwording() {
    }
    
    public String hashPassword(String password, String salt) {
	String hashment = salt + password + salt;
	String hashed = hash(hashment);
	return hashed;
    }
    
    public String generateSalt() {
	long value = random.nextLong();
	return Long.toHexString(value);
    }
    
    private String hash(String value) {
	try {
	    MessageDigest md = MessageDigest.getInstance(ALGORITHM);
	    
	    byte[] valueBytes = value.getBytes();
	    byte[] hashBytes = md.digest(valueBytes);
	    String hashStr = DatatypeConverter.printBase64Binary(hashBytes);
	    
	    return hashStr;
	} catch (Exception ex) {
	    throw new RuntimeException("Cannot hash password", ex);
	}
    }
    
    
}
