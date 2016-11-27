package cz.martlin.douckonline.business.model.managment;

import cz.martlin.douckonline.business.model.base.EntityWithLongID;
import cz.martlin.douckonline.business.model.lector.Lector;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
@Entity
public class RequestReaction extends EntityWithLongID {

    @ManyToOne
    private Lector lector;

    @Enumerated
    private RequestReactionStatus status;

    @Temporal(TemporalType.TIME)
    private Calendar when;

    @Size(min = 0, max = 200)
    private String description;

    public RequestReaction() {
	super();

    }

    public RequestReaction(Lector lector, RequestReactionStatus status, Calendar when, String description) {
	super();

	this.lector = lector;
	this.status = status;
	this.when = when;
	this.description = description;
    }

    public Lector getLector() {
	return lector;
    }

    public void setLector(Lector lector) {
	this.lector = lector;
    }

    public RequestReactionStatus getStatus() {
	return status;
    }

    public void setStatus(RequestReactionStatus status) {
	this.status = status;
    }

    public Calendar getWhen() {
	return when;
    }

    public void setWhen(Calendar when) {
	this.when = when;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    @Override
    public String toString() {
	return "cz.martlin.douckonline.business.model.managment.RequestReaction[ id=" + id + " ]";
    }

}
