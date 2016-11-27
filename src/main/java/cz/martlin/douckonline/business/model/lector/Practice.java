package cz.martlin.douckonline.business.model.lector;

import cz.martlin.douckonline.business.model.base.EntityWithLongID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
@Entity
@Table(name = "practices")
public class Practice extends EntityWithLongID {

    @Column(name = "work")
    @Size(min = 1, max = 20)
    private String work;

    @Column(name = "institution")
    @Size(min = 0, max = 80)
    private String institution;

    public Practice() {
	super();

    }

    public Practice(String work, String institution) {
	super();

	this.work = work;
	this.institution = institution;
    }

    public String getInstitution() {
	return institution;
    }

    public void setInstitution(String institution) {
	this.institution = institution;
    }

    public String getWork() {
	return work;
    }

    public void setWork(String work) {
	this.work = work;
    }

    @Override
    public String toString() {
	return "Practice{" + "id=" + id + ", work=" + work + ", institution=" + institution + '}';
    }

}
