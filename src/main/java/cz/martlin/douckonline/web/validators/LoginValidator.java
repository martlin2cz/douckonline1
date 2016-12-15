package cz.martlin.douckonline.web.validators;

import cz.martlin.douckonline.business.logic.Users;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Implements loging in validator.
 * @author m@rtlin <martlin@seznam.cz>
 */
@FacesValidator("loginValidator")
public class LoginValidator implements Validator {

    private final Users USERS = new Users();
    
    public LoginValidator() {
    }
    
    

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
	UIInput usernameTextBox = (UIInput) component.getAttributes().get("usernameBinding");
	
	if (!usernameTextBox.isValid()) {
	    return;
	}
	
	String username = (String) usernameTextBox.getValue();
	String password = (String) value;
	
	boolean valid = USERS.isValid(username, password);
	if (!valid) {
	    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
		    "Login failed", "Invalid username or password");
	    throw new ValidatorException(message);
	}
    }


    
    
}
