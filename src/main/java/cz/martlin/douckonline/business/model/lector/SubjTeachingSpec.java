package cz.martlin.douckonline.business.model.lector;

import cz.martlin.douckonline.business.model.teaching.Level;
import cz.martlin.douckonline.business.model.teaching.Subject;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
@Entity
@Table( name = "subject_teaching_specification")
public class SubjTeachingSpec implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private Subject subject;
    
    @Column(name = "level")
    @Enumerated
    private Level level;
    
    @Column(name = "suitability")
    @Enumerated
    private Suitability suitabity;
    
    @Column(name = "aditional_description")
    @Size(min = 0, max = 30)
    private String additionalDescription;

    @Column(name = "cost")
    @Min(0)
    private int cost;
    
    public SubjTeachingSpec() {
    }

    public SubjTeachingSpec(int id, Subject subject, Level level, Suitability suitabity, String additionalDescription, int cost) {
	this.id = id;
	this.subject = subject;
	this.level = level;
	this.suitabity = suitabity;
	this.additionalDescription = additionalDescription;
	this.cost = cost;
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
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
    public int hashCode() {
	int hash = 0;
	hash += id;
	return hash;
    }

    @Override
    public boolean equals(Object object) {
	if (!(object instanceof SubjTeachingSpec)) {
	    return false;
	}
	SubjTeachingSpec other = (SubjTeachingSpec) object;
	if (this.id != other.id) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "SubjTeachingSpec{id=" + id + ", subj= " + subject + " }";
    }
    
}
