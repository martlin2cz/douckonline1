package cz.martlin.douckonline.web.converters;

import cz.martlin.douckonline.business.logic.Users;
import cz.martlin.douckonline.business.model.base.User;
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
@Named("usersConverter")
public class UsersConverter implements  Converter {

    private final Users users = new Users();

    
    public UsersConverter() {
    }
    
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
	String loginName = value;
	User user = users.findUser(value);
	return user;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
	User user = (User) value;
	String loginName = user.getLoginName();
	return loginName;
    }
    
}
