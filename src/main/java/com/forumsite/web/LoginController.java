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
   
   public void login(){
       System.out.println("Login clicked");
       AuthenticationResult result = identity.login();
       System.out.println(result);
       if(AuthenticationResult.FAILED.equals(result)){
           fctx.addMessage(null, new FacesMessage("Authentication was unsuccessful.  Please check your username and password before trying again."));
       }
   }
   
    
}
