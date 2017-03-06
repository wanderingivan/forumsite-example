package com.forumsite.web.user;

import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;

import com.forumsite.model.User;
import com.forumsite.service.UserManagement;

@Model
public class UserController {

    @Inject
    private FacesContext ctx;
    
    @Inject
    private UserManagement umgmt;
    
    @Inject
    private Logger logger;
    
    @Named
    @Produces
    private User user; 

    private Part imageFile;
    
    
    @PostConstruct
    public void initNewUser(){
        user = new User();
    }
    
    public String register(){
        if(logger.isInfoEnabled()){
            logger.info("UserController adding new user " + user);
        }
        try{
           umgmt.saveUser(user,Optional.ofNullable(imageFile));
           return "loadUser?faces-redirect=true&username="+user.getUsername();
        }catch(Exception e){
            logger.error(String.format("Exception caught persisting user %s \n %s ",user,e));
            ctx.addMessage(null, new FacesMessage("There was an error registering this user"));
        }
           return "";
    }
    
    public void edit(){
        if(logger.isInfoEnabled()){
            logger.info("UserController updating user " + user);
        }
        try{
            umgmt.updateUser(user,Optional.ofNullable(imageFile));
            initNewUser();
         }catch(Exception e){
             logger.error(String.format("Exception caught updating user %s \n %s ",user,e));
         }        
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Part getImageFile() {
        return imageFile;
    }

    public void setImageFile(Part imageFile) {
        this.imageFile = imageFile;
    }
    
}
