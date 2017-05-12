package com.forumsite.web;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;

import org.picketlink.Identity;
import org.picketlink.Identity.AuthenticationResult;



@Named
@Stateless
public class LoginController extends AbstractController {

   @Inject
   private Identity identity;
   
   public String login(){
       AuthenticationResult result = identity.login();
       System.out.println(result);
       if(AuthenticationResult.FAILED.equals(result)){
           ctx.addMessage(null, new FacesMessage(getMessage("auth_failure")));
           return "";
       }
       return "main.jsf?faces-redirect=true";
   }
   
   public String logout(){
       identity.logout();
       return "/main.jsf?faces-redirect=true";
   }
   
}
