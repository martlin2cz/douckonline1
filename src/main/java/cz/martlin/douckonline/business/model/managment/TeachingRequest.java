package cz.martlin.douckonline.business.model.managment;

import cz.martlin.douckonline.business.model.base.EntityWithLongID;
import cz.martlin.douckonline.business.model.teaching.Level;
import cz.martlin.douckonline.business.model.teaching.Subject;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
@Entity
@Table(name = "teaching_request")
public class TeachingRequest extends EntityWithLongID {

    @Column(name = "register_name")
    @Size(min = 2, max = 50)
    private String registerName;

    @Column(name = "student_name")
    @Size(min = 2, max = 10)
    private String studentName;

    @Column(name = "email")
    @Size(min = 2, max = 50)
    private String email;

    @Column(name = "phone")
    @Size(min = 9, max = 15)
    private String phone;

    @ManyToOne
    @JoinColumn(name = "subject_name")
    private Subject subject;

    @Column(name = "level")
    @Enumerated(EnumType.STRING)
    private Level level;

    @Column(name = "description")
    @Size(min = 0, max = 200)
    private String description;

    @OneToMany
    @JoinColumn(name = "teaching_request_id")
    private List<RequestReaction> reactions;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private TeachingRequestStatus status;

    public TeachingRequest() {
	super();

    }

    public TeachingRequest(String registerName, String studentName, String email, String phone, Subject subject, Level level, String description, List<RequestReaction> reactions, TeachingRequestStatus status) {
	super();

	this.registerName = registerName;
	this.studentName = studentName;
	this.email = email;
	this.phone = phone;
	this.subject = subject;
	this.level = level;
	this.description = description;
	this.reactions = reactions;
	this.status = status;
    }

    public String getRegisterName() {
	return registerName;
    }

    public void setRegisterName(String registerName) {
	this.registerName = registerName;
    }

    public String getStudentName() {
	return studentName;
    }

    public void setStudentName(String studentName) {
	this.studentName = studentName;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getPhone() {
	return phone;
    }

    public void setPhone(String phone) {
	this.phone = phone;
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

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public List<RequestReaction> getReactions() {
	return reactions;
    }

    public void setReactions(List<RequestReaction> reactions) {
	this.reactions = reactions;
    }

    public TeachingRequestStatus getStatus() {
	return status;
    }

    public void setStatus(TeachingRequestStatus status) {
	this.status = status;
    }

    @Override
    public String toString() {
	return "TeachingRequest{id=" + id + ", subject=" + subject + ", ...}";
    }

}