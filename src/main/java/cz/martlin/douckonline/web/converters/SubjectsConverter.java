package cz.martlin.douckonline.web.converters;

import cz.martlin.douckonline.business.logic.Subjects;
import cz.martlin.douckonline.business.model.teaching.Subject;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

/**
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
@RequestScoped
@Named("subjectsConverter")
public class SubjectsConverter implements  Converter {

    private final Subjects SUBJECTS = new Subjects();
    
    
    public SubjectsConverter() {
    }
    
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
	if (value == null) {
	    return null;
	}
	String subjectName = value;
	Subject subject = SUBJECTS.getSubject(subjectName);
	return subject;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
	if (value == null) {
	    return null;
	}
	Subject subject = (Subject) value;
	String subjectName = subject.getName();
	return subjectName;
    }
    
}
