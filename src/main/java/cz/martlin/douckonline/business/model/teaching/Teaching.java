package cz.martlin.douckonline.business.model.teaching;

import cz.martlin.douckonline.business.model.base.EntityWithLongID;
import cz.martlin.douckonline.business.model.lector.Lector;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
@Entity
@Table(name = "teaching")
public class Teaching extends EntityWithLongID {

    @ManyToOne
    private Lector lector;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Subject subject;

    @Enumerated
    private Level level;

    @Min(0)
    private int cost;

    @OneToMany
    private List<Lesson> lessons;

    @Temporal(TemporalType.DATE)
    private Calendar startedAt;

    @Temporal(TemporalType.DATE)
    private Calendar endedAt;

    @Column(name = "status_description")
    @Size(min = 0, max = 200)
    private String statusDescription;

    public Teaching() {
	super();

    }

    public Teaching(Lector lector, Student student, Subject subject, Level level, int cost, List<Lesson> lessons, Calendar startedAt, Calendar endedAt, String statusDescription) {
	super();

	this.lector = lector;
	this.student = student;
	this.subject = subject;
	this.level = level;
	this.cost = cost;
	this.lessons = lessons;
	this.startedAt = startedAt;
	this.endedAt = endedAt;
	this.statusDescription = statusDescription;
    }

    public Lector getLector() {
	return lector;
    }

    public void setLector(Lector lector) {
	this.lector = lector;
    }

    public Student getStudent() {
	return student;
    }

    public void setStudent(Student student) {
	this.student = student;
    }

    public Subject getSubject() {
	return subject;
    }

    public void setSubject(Subject subject) {
	this.subject = subject;
    }

    public Level getLevel() {
	return level;
    }

    public void setLevel(Level level) {
	this.level = level;
    }

    public int getCost() {
	return cost;
    }

    public void setCost(int cost) {
	this.cost = cost;
    }

    public List<Lesson> getLessons() {
	return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
	this.lessons = lessons;
    }

    public Calendar getStartedAt() {
	return startedAt;
    }

    public void setStartedAt(Calendar startedAt) {
	this.startedAt = startedAt;
    }

    public Calendar getEndedAt() {
	return endedAt;
    }

    public void setEndedAt(Calendar endedAt) {
	this.endedAt = endedAt;
    }

    public String getStatusDescription() {
	return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
	this.statusDescription = statusDescription;
    }

    @Override
    public String toString() {
	return "Teaching{ id=" + id + ", lector = " + lector.getLoginName() + ", student = " + student.getLoginName() + ", subject = " + subject.getName() + "}";
    }

}
