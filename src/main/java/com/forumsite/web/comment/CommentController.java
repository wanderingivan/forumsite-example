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
    
    private String quote;
    
    private String quoted;
    
    
    public String addComment(){
        if(logger.isTraceEnabled()){
            logger.trace("Adding new comment " + message + " for thread " + threadName );
        }
        cmgmt.addComment(new Comment(message),threadName);
        return "loadThread?faces-redirect=true&threadname="+threadName;
    }


    public String getMessage() {
        if(getQuote() != null  && getQuoted() != null){
            message = String.format("> %s said: %s ", getQuoted(),getQuote()); 
        }
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


    public String getQuote() {
        return quote;
    }


    public void setQuote(String quote) {
        this.quote = quote;
    }


    public String getQuoted() {
        return quoted;
    }


    public void setQuoted(String quoted) {
        this.quoted = quoted;
    }
    
}
