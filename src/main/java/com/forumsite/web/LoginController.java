package com.forumsite.web;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.picketlink.Identity;
import org.picketlink.Identity.AuthenticationResult;


@Named
@Stateless
public class LoginController {

   @Inject
   private Identity identity;
   
   @Inject
   private FacesContext fctx;
   
   public String login(){
       AuthenticationResult result = identity.login();
       if(AuthenticationResult.FAILED.equals(result)){
           fctx.addMessage(null, new FacesMessage("Authentication was unsuccessful.  Please check your username and password before trying again."));
           return "";
       }
       return "main.jsf?faces-redirect=true";
   }
   
    
}
