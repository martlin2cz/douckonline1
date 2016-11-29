package cz.martlin.douckonline.business.model.lector;

import cz.martlin.douckonline.business.model.base.EntityWithLongID;
import cz.martlin.douckonline.business.model.teaching.Level;
import cz.martlin.douckonline.business.model.teaching.Subject;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
@Entity
@Table(name = "subject_teaching_specifications")
public class SubjTeachingSpec extends EntityWithLongID {

    @ManyToOne
    @JoinColumn(name = "lector_login_name")
    private Lector lector;
    
    @ManyToOne
    @JoinColumn(name = "subject_name")
    private Subject subject;

    @Column(name = "level")
    @Enumerated(EnumType.STRING)
    private Level level;

    @Column(name = "suitability")
    @Enumerated(EnumType.STRING)
    private Suitability suitabity;

    @Column(name = "aditional_description")
    @Size(min = 0, max = 30)
    private String additionalDescription;

    @Column(name = "cost")
    @Min(0)
    private int cost;

    public SubjTeachingSpec() {
	super();

    }

    public SubjTeachingSpec(Subject subject, Level level, Suitability suitabity, String additionalDescription, int cost, Lector lector) {
	super();

	this.subject = subject;
	this.level = level;
	this.suitabity = suitabity;
	this.additionalDescription = additionalDescription;
	this.cost = cost;
	this.lector = lector;
    }

    public Lector getLector() {
	return lector;
    }

    public void setLector(Lector lector) {
	this.lector = lector;
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

    public Suitability getSuitabity() {
	return suitabity;
    }

    public void setSuitabity(Suitability suitabity) {
	this.suitabity = suitabity;
    }

    public String getAdditionalDescription() {
	return additionalDescription;
    }

    public void setAdditionalDescription(String additionalDescription) {
	this.additionalDescription = additionalDescription;
    }

    public int getCost() {
	return cost;
    }

    public void setCost(int cost) {
	this.cost = cost;
    }

    @Override
    public String toString() {
	return "SubjTeachingSpec{id=" + id + ", subj= " + subject + " }";
    }

}
