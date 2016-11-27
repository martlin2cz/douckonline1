package cz.martlin.douckonline.business.model.teaching;

import cz.martlin.douckonline.business.model.base.EntityWithLongID;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
@Entity
@Table(name = "lessons")
public class Lesson extends EntityWithLongID {

    @ManyToOne
    private Teaching teaching;

    @Temporal(TemporalType.DATE)
    @Column(name = "added_at")
    private Calendar addedAt;

    @Temporal(TemporalType.DATE)
    private Calendar date;

    @Temporal(TemporalType.TIME)
    private Calendar duration;

    @Size(min = 1, max = 200)
    private String description;

    public Lesson() {
	super();

    }

    public Lesson(Teaching teaching, Calendar date, Calendar duration, String description) {
	super();

	this.teaching = teaching;
	this.date = date;
	this.duration = duration;
	this.description = description;
    }

    public Teaching getTeaching() {
	return teaching;
    }

    public void setTeaching(Teaching teaching) {
	this.teaching = teaching;
    }

    public Calendar getDate() {
	return date;
    }

    public void setDate(Calendar date) {
	this.date = date;
    }

    public Calendar getDuration() {
	return duration;
    }

    public void setDuration(Calendar duration) {
	this.duration = duration;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    @Override
    public String toString() {
	return "Lesson{id = " + id + ", teaching = " + teaching.getId() + ", date = " + date.getTime() + "}";
    }

}
