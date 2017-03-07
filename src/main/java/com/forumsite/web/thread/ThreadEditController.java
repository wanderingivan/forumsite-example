package com.forumsite.web.thread;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;

import com.forumsite.model.ForumThread;
import com.forumsite.service.ForumThreadManagement;
import com.forumsite.service.ForumThreadProducer;

@Named()
@SessionScoped
public class ThreadEditController implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 2097796810404302279L;

    @Inject
    private FacesContext ctx;
    
    @Inject
    private transient ForumThreadManagement fmgmt;
    
    @Inject
    private transient ForumThreadProducer producer;
    
    @Inject
    private transient Logger logger;
    
    private ForumThread thread; 

    
    private String threadname;
    

    
    public void load(){
        thread = producer.getThread(threadname);
    }
    
    public String update(){
        if(logger.isInfoEnabled()){
            logger.info("EditController updating thread " + thread);
        }
        try{
           fmgmt.updateThread(thread);
           return "loadThread?faces-redirect=true&threadname="+thread.getName();
        }catch(Exception e){
            logger.error(String.format("Exception caught updating user  %s \n %s ",thread,e));
            ctx.addMessage(null, new FacesMessage("There was an error updating this thread"));
        }
           return "";
    }

    public ForumThread getThread() {
        return thread;
    }

    public void setThread(ForumThread thread) {
        this.thread = thread;
    }

    public String getThreadname() {
        return threadname;
    }

    public void setThreadname(String threadname) {
        this.threadname = threadname;
    }

}
