package com.forumsite.util.validation;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

import com.forumsite.service.ForumThreadManagement;

@FacesValidator("threadNameValidator")
public class ThreadNameValidator extends AbstractDuplicateFieldValidator {

    @Inject
    private ForumThreadManagement threadService;
    
    @Override
    public void validate(FacesContext ctx, UIComponent component, Object value) throws ValidatorException {
        if(!threadService.isNameAvailable((String) value)){
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,getMessage(ctx,"duplicate_threadname"),null)); 
        }
    }

}
