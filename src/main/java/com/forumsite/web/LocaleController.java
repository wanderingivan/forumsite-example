package com.forumsite.web;

import java.io.Serializable;
import java.util.Locale;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named("language")
@SessionScoped
public class LocaleController extends AbstractController implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 5265604549222640252L;

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
    
    public String changeLocale(String locale){
        this.locale = new Locale(locale);
        ctx.getViewRoot().setLocale(this.locale);
        return "/main.jsf?faces-redirect=true";
    }
    
}