package cz.martlin.douckonline.business.model.base;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 * Base user entity class. Has a login credentials (username, password (salt and
 * hash)), and date of registration and last login.
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
@MappedSuperclass
public abstract class User implements Serializable {

    @Id
    @Column(name = "login_name")
    @Size(min = 1, max = 50)
    private String loginName;

    @Column(name = "password_hash")
    @Size(min = 1, max = 100)
    private String passwordHash;

    @Column(name = "password_salt")
    @Size(min = 1, max = 100)
    private String passwordSalt;

    @Column(name = "registered_at")
    @Temporal(TemporalType.DATE)
    private Calendar registeredAt;

    @Column(name = "last_login_at")
    @Temporal(TemporalType.DATE)
    private Calendar lastLoginAt;

    public User() {
    }

    public User(String loginName, String passwordHash, String passwordSalt, Calendar registeredAt, Calendar lastLoginAt) {
	this.loginName = loginName;
	this.passwordHash = passwordHash;
	this.passwordSalt = passwordSalt;
	this.registeredAt = registeredAt;
	this.lastLoginAt = lastLoginAt;
    }

    public abstract String getDisplayName();

    public abstract String getReallName();

    public String getLoginName() {
	return loginName;
    }

    public void setLoginName(String loginName) {
	this.loginName = loginName;
    }

    public String getPasswordHash() {
	return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
	this.passwordHash = passwordHash;
    }

    public String getPasswordSalt() {
	return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
	this.passwordSalt = passwordSalt;
    }

    public Calendar getRegisteredAt() {
	return registeredAt;
    }

    public void setRegisteredAt(Calendar registeredAt) {
	this.registeredAt = registeredAt;
    }

    public Calendar getLastLoginAt() {
	return lastLoginAt;
    }

    public void setLastLoginAt(Calendar lastLoginAt) {
	this.lastLoginAt = lastLoginAt;
    }

    @Override
    public int hashCode() {
	int hash = 3;
	hash = 97 * hash + Objects.hashCode(this.loginName);
	return hash;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (obj == null) {
	    return false;
	}
	if (getClass() != obj.getClass()) {
	    return false;
	}
	final User other = (User) obj;
	if (!Objects.equals(this.loginName, other.loginName)) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "User{" + loginName + "}";
    }

}
