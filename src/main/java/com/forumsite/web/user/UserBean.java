package com.forumsite.web.user;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.NoResultException;

import org.apache.log4j.Logger;

import com.forumsite.model.User;
import com.forumsite.service.UserProducer;

@Model
public class UserBean {

    @Inject
    private Logger logger;
    
    @Inject
    private FacesContext ctx;
    
    @Inject
    private UserProducer producer;
    
    private String username;
    
    private User user;
    
    @PostConstruct
    public void init(){
        user = new User();
    }
    public void load(){
        if(logger.isDebugEnabled()){
            logger.debug("Loading user "  + user.getUsername());
        }
        try{
            user  = producer.getUser(username);
        }catch(NoResultException e){
            System.out.println("Inside no result exception block");
            ctx.addMessage(null, new FacesMessage("Sorry, there is no user with the name " + username));
        }catch(Exception e){
            System.out.println("Inside general exception block");
            logger.error(String.format("Exception caught loading user %s :\n %s",username,e));
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User getUser() {
        return user;
    }
    
}
