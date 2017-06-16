package com.forumsite.util.validation;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

import com.forumsite.service.UserManagement;

@FacesValidator("emailFieldValidator")
public class EmailFieldValidator extends AbstractDuplicateFieldValidator {

    @Inject
    private UserManagement userService;
    
    @Override
    public void validate(FacesContext ctx, UIComponent component, Object value) throws ValidatorException {
        if(!userService.isEmailAvailable((String) value)){
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,getMessage(ctx,"duplicate_email"),null)); 
        }
    }

}
