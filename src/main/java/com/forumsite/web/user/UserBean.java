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

    private final int DEFAULT_OFFSET = 0,COMMENTS_PER_PAGE=10;

    private int fromComment;
    
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
            ctx.addMessage(null, new FacesMessage("Sorry, there is no user with the name " + username));
        }catch(Exception e){
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
    
    public int getFromComment() {
        return fromComment == 0 ? DEFAULT_OFFSET : fromComment;
    }

    public void setFromComment(int fromComment) {
        this.fromComment = fromComment;
    }
    
    public int getCommentsPerPage(){
        int reqSize = getFromComment() + COMMENTS_PER_PAGE;
        int total = user.getComments().size();
        return reqSize > total ? total : reqSize;
    }
    
}
