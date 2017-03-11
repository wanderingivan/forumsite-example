package com.forumsite.web.user;

import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
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
    
    private User user; 

    private Part imageFile;
    
    
    @PostConstruct
    public void initNewUser(){
        user = new User();
    }
    
    public String register() throws Exception{
        if(logger.isInfoEnabled()){
            logger.info("UserController adding new user " + user);
        }
        umgmt.saveUser(user,Optional.ofNullable(imageFile));
        return "loadUser?faces-redirect=true&username="+user.getUsername();
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
