package com.forumsite.web.user;

import java.io.Serializable;
import java.util.Optional;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;

import com.forumsite.model.User;
import com.forumsite.service.UserManagement;
import com.forumsite.service.UserProducer;

@Named()
@SessionScoped
public class EditController implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 2097796810404302279L;

    @Inject
    private FacesContext ctx;
    
    @Inject
    private transient UserManagement umgmt;
    
    @Inject
    private transient UserProducer producer;
    
    @Inject
    private transient Logger logger;
    
    private User user; 

    private Part imageFile;
    
    private String username;
    
    public void init(){
        user = new User();
    }
    
    public void load(){
        user = producer.getUser(username);
    }
    
    public String update(){
        if(logger.isInfoEnabled()){
            logger.info("EditController updating user " + user);
        }
        try{
           umgmt.updateUser(user,Optional.ofNullable(imageFile));
           return "loadUser?faces-redirect=true&username="+user.getUsername();
        }catch(Exception e){
            logger.error(String.format("Exception caught updating user  %s \n %s ",user,e));
            ctx.addMessage(null, new FacesMessage("There was an error updating this user"));
        }
           return "";
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
