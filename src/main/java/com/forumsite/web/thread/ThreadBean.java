package com.forumsite.web.thread;


import javax.enterprise.inject.Model;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.forumsite.model.ForumThread;
import com.forumsite.service.ForumThreadProducer;

@Model
public class ThreadBean {

    @Inject
    private Logger logger;
    
    @Inject
    private ForumThreadProducer producer;
    
    private String threadName;
    
    private ForumThread topic;
    
    public void load(){
        if(logger.isDebugEnabled()){
            logger.debug("Loading thread with name " + threadName);
        }
        topic = producer.getThread(threadName);
        //return "loadThread";
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public ForumThread getTopic() {
        return topic;
    }
    
}
