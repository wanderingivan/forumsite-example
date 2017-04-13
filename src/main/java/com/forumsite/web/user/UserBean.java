package com.forumsite.web.user;

import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.forumsite.model.User;
import com.forumsite.service.UserProducer;
import com.forumsite.web.AbstractController;

@Model
public class UserBean extends AbstractController {

    @Inject
    private Logger logger;
    
    @Inject
    private UserProducer producer;
    
    private String username;

    private final int DEFAULT_OFFSET = 0, COMMENTS_PER_PAGE= 10;

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
        Optional<User> o = producer.getUser(username);
        if(o.isPresent()){
            user  = o.get();
        }else{
            ctx.addMessage(null, new FacesMessage(getMessage("no_user") + username));
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
