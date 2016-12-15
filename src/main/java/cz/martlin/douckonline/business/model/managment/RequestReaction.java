package cz.martlin.douckonline.business.model.managment;

import cz.martlin.douckonline.business.model.base.EntityWithLongID;
import cz.martlin.douckonline.business.model.lector.Lector;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Lector's reaction to teaching request. Contains just status (accept/deny),
 * description and timestamp.
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
@Entity
@Table(name = "request_reactions")
@XmlRootElement
public class RequestReaction extends EntityWithLongID {

    @ManyToOne
    @JoinColumn(name = "teaching_request_id")
    private TeachingRequest request;

    @ManyToOne
    @JoinColumn(name = "lector_name")
    private Lector lector;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private RequestReactionStatus status;

    @Column(name = "`when`")
    @Temporal(TemporalType.TIME)
    private Calendar when;

    @Column(name = "description")
    @Size(min = 0, max = 200)
    private String description;

    public RequestReaction() {
	super();

    }

    public RequestReaction(TeachingRequest request, Lector lector, RequestReactionStatus status, Calendar when, String description) {
	super();

	this.request = request;
	this.lector = lector;
	this.status = status;
	this.when = when;
	this.description = description;
    }

    public TeachingRequest getRequest() {
	return request;
    }

    public void setRequest(TeachingRequest request) {
	this.request = request;
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
	return "RequestReaction{id=" + id + ", lector=" + lector.getLoginName() + ", request=" + request.getId() + ", description=" + description + "}";
    }

}
