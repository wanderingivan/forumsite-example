package com.forumsite.web.thread;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.forumsite.model.ForumThread;
import com.forumsite.service.ForumThreadManagement;

@Model
public class ThreadCreateController {
    
    @Inject
    private FacesContext ctx;
    
    @Inject
    private ForumThreadManagement fmgmt;
    
    @Inject
    private Logger logger;
    
    private ForumThread newThread; 
    
    private String initialMessage;
    
    @PostConstruct
    public void initNewThread(){
        newThread = new ForumThread();
    }
    
    public String create(){
        if(logger.isInfoEnabled()){
            logger.info("ForumThreadController adding new thread" + newThread);
        }        

        fmgmt.saveThread(newThread,initialMessage);
        return "/thread/loadThread?faces-redirect=true&threadname="+newThread.getName();
    }

    public ForumThread getNewThread() {
        return newThread;
    }

    public void setNewThread(ForumThread newThread) {
        this.newThread = newThread;
    }

    public String getInitialMessage() {
        return initialMessage;
    }

    public void setInitialMessage(String initialMessage) {
        this.initialMessage = initialMessage;
    }
    
}
