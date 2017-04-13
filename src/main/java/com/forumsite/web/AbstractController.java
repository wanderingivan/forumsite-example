package com.forumsite.web;

import javax.faces.context.FacesContext;
import javax.inject.Inject;

public abstract class AbstractController {

    @Inject
    protected FacesContext ctx;
    
  
    protected String getMessage(String key){
       return ctx.getApplication()
                 .getResourceBundle(ctx, "msg")
                 .getString(key);
   } 
    
}
