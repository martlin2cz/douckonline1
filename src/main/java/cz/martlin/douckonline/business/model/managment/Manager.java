package cz.martlin.douckonline.business.model.managment;

import cz.martlin.douckonline.business.model.managment.User;
import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
@Entity
@Table(name = "managers")
public class Manager extends User implements Serializable {
    
    private String realName;
    
    
    public Manager() {
	super();
    }
    
    public Manager(String realName, String loginName, String passwordHash, String passwordSalt, Calendar registeredAt, Calendar lastLoginAt) {
	super(loginName, passwordHash, passwordSalt, registeredAt, lastLoginAt);
	this.realName = realName;
    }

    public String getRealName() {
	return realName;
    }

    public void setRealName(String realName) {
	this.realName = realName;
    }

    @Override
    public String toString() {
	return "Manager{" + getLoginName() + "}";
    }
    
}
