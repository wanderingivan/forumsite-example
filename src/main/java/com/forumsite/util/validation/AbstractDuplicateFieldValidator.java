package com.forumsite.util.validation;

import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;

public abstract class AbstractDuplicateFieldValidator implements Validator {
    
    protected String getMessage(FacesContext ctx, String key){
        return ctx.getApplication()
                  .getResourceBundle(ctx, "msg")
                  .getString(key);
    } 
}
