package cz.martlin.douckonline.business.logic;

import java.security.MessageDigest;
import java.util.Random;
import javax.xml.bind.DatatypeConverter;


/**
 * Implements working with passwords. 
 * @author m@rtlin <martlin@seznam.cz>
 */
public class Passwording {
    private static final String ALGORITHM = "MD5";
    
    private final Random random = new Random();

    public Passwording() {
    }
    
    /**
     * Computes hash of given password with given salt.
     * @param password
     * @param salt
     * @return 
     */
    public String hashPassword(String password, String salt) {
	String hashment = salt + password + salt;
	String hashed = hash(hashment);
	return hashed;
    }
    
    /**
     * Generates random salt.
     * @return 
     */
    public String generateSalt() {
	long value = random.nextLong();
	return Long.toHexString(value);
    }
    
    /**
     * Hashs given string.
     * @param value
     * @return 
     */
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
