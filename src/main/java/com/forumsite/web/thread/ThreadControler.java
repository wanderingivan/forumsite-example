package com.forumsite.web.thread;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.forumsite.model.ForumThread;
import com.forumsite.service.ForumThreadManagement;

@Model
public class ThreadControler {
    
    @Inject
    private FacesContext ctx;
    
    @Inject
    private ForumThreadManagement fmgmt;
    
    @Inject
    private Logger logger;
    
    private ForumThread newThread; 
    
    @PostConstruct
    public void initNewThread(){
        newThread = new ForumThread();
    }
    
    public String create(){
        if(logger.isInfoEnabled()){
            logger.info("ForumThreadController adding new user " + newThread);
        }        
        try{
            fmgmt.saveThread(newThread);
            return "loadThread?faces-redirect=true&threadname="+newThread.getName();
        }catch(Exception e){
            logger.error(String.format("Exception caught persisting thread %s \n %s ",newThread,e));
            ctx.addMessage(null, new FacesMessage("There wan an error creating this thread. Please try again later."));
        }
        return "";
    }

    public ForumThread getNewThread() {
        return newThread;
    }

    public void setNewThread(ForumThread newThread) {
        this.newThread = newThread;
    }
    
}
