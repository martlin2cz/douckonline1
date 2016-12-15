package cz.martlin.douckonline.business.model.lector;

import cz.martlin.douckonline.business.model.base.EntityWithLongID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * Represents work experience, study abroad or practice of lector.
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
@Entity
@Table(name = "practices")
public class Practice extends EntityWithLongID {

    @Column(name = "`work`")
    @Size(min = 1, max = 20)
    private String work;

    @Column(name = "institution")
    @Size(min = 0, max = 80)
    private String institution;

    @ManyToOne
    @JoinColumn(name = "lector_login_name")
    private Lector lector;

    public Practice() {
	super();

    }

    public Practice(String work, String institution, Lector lector) {
	this.work = work;
	this.institution = institution;
	this.lector = lector;
    }

    public String getWork() {
	return work;
    }

    public void setWork(String work) {
	this.work = work;
    }

    public String getInstitution() {
	return institution;
    }

    public void setInstitution(String institution) {
	this.institution = institution;
    }

    public Lector getLector() {
	return lector;
    }

    public void setLector(Lector lector) {
	this.lector = lector;
    }

    @Override
    public String toString() {
	return "Practice{" + "id=" + id + ", work=" + work + ", institution=" + institution + '}';
    }

}
