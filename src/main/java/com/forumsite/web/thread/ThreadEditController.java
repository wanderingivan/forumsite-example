package com.forumsite.web.thread;

import java.io.Serializable;

import javax.ejb.Stateful;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;

import com.forumsite.model.ForumThread;
import com.forumsite.service.ForumThreadManagement;
import com.forumsite.service.ForumThreadProducer;

@Named()
@Stateful
@ConversationScoped
public class ThreadEditController implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 2097796810404302279L;

    @Inject
    private transient ForumThreadManagement fmgmt;
    
    @Inject
    private transient ForumThreadProducer producer;
    
    @Inject
    private transient Logger logger;
    
    @Inject
    private Conversation conversation;
    
    private ForumThread thread; 
    
    private String threadname;
    
    public void load(){
        if(thread == null){
            conversation.begin();
            thread = producer.getThread(threadname)
                             .orElseThrow(() -> new IllegalArgumentException("Tried to update non-existing thread"));
        }
    }
    
    public String update(){
        if(logger.isInfoEnabled()){
            logger.info("EditController updating thread " + thread);
        }
        fmgmt.updateThread(thread);
        conversation.end();
        return "loadThread?faces-redirect=true&threadname="+thread.getName();
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
