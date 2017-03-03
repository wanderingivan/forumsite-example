package com.forumsite.web;

import java.io.Serializable;
import java.util.Locale;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named("language")
@SessionScoped
public class LocaleController implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 5265604549222640252L;

    @Inject
    private FacesContext ctx;
    
    private Locale locale;
    
    public String getLanguage(){
        return getLocale().getLanguage(); 
    }
    
    public Locale getLocale(){
        if(locale == null){
            this.locale = ctx.getViewRoot().getLocale();
        }
        return this.locale;
    }
    
    public void changeLocale(String locale){
        this.locale = new Locale(locale);
        ctx.getViewRoot().setLocale(this.locale);
    }
    
}