package com.forumsite.web.user;

import java.io.Serializable;
import java.util.Optional;

import javax.ejb.Stateful;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;

import com.forumsite.model.User;
import com.forumsite.service.UserManagement;
import com.forumsite.service.UserProducer;

@Named()
@Stateful
@ConversationScoped
public class EditController implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 2097796810404302279L;
    
    @Inject
    private transient UserManagement umgmt;
    
    @Inject
    private transient UserProducer producer;
    
    @Inject
    private transient Logger logger;
    
    @Inject
    private Conversation conversation;
    
    private User user; 

    private Part imageFile;
    
    private String username;
    
    public void init(){
        user = new User();
    }
    
    public void load(){
        if(user == null){
            conversation.begin();
            user = producer.getUser(username)
                           .orElseThrow(() -> new IllegalArgumentException("Tried to update non-existing user"));
        }
    }
    
    public String update() throws Exception{
        if(logger.isInfoEnabled()){
            logger.info("EditController updating user " + user);
        }
        umgmt.updateUser(user,Optional.ofNullable(imageFile));
        conversation.end();
        return "/user/loadUser?faces-redirect=true&username="+user.getUsername();
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
