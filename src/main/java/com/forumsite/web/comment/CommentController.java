package com.forumsite.web.comment;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;

import com.forumsite.model.Comment;
import com.forumsite.service.CommentManagement;

@Named
@RequestScoped
public class CommentController {

    @Inject
    private FacesContext ctx;
    
    @Inject
    private CommentManagement cmgmt;
    
    @Inject
    private Logger logger;
    
    private String message;
    
    private String threadName;
    
    
    public String addComment(){
        cmgmt.addComment(new Comment(message),threadName);
        return "loadThread?faces-redirect=true&threadname="+threadName;
    }


    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }


    public String getThreadName() {
        return threadName;
    }


    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }
   
}
