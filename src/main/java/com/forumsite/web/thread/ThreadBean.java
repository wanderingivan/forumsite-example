package com.forumsite.web.thread;


import java.util.Optional;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.forumsite.model.ForumThread;
import com.forumsite.service.ForumThreadProducer;

@Model
public class ThreadBean {

    @Inject
    private FacesContext ctx;
    
    @Inject
    private Logger logger;
    
    @Inject
    private ForumThreadProducer producer;
    
    private final int DEFAULT_OFFSET = 0,COMMENTS_PER_PAGE=10;
    
    private int fromComment;
    
    private String threadName;
    
    private ForumThread topic;
    
    public void load(){
        if(logger.isDebugEnabled()){
            logger.debug("Loading thread with name " + threadName);
        }
        Optional<ForumThread> o = producer.getThread(threadName);
        if(o.isPresent()){
            topic = producer.getThread(threadName)
                            .get();
        }else{
            ctx.addMessage(null, new FacesMessage(getMessage("no_thread") + threadName));
        }
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

    public int getFromComment() {
        return fromComment == 0 ? DEFAULT_OFFSET : fromComment;
    }

    public void setFromComment(int fromComment) {
        this.fromComment = fromComment;
    }
    
    public int getCommentsPerPage(){
        int reqSize = getFromComment() + COMMENTS_PER_PAGE;
        int total = topic.getComments().size();
        return reqSize > total ? total : reqSize;
    }
    
    private String getMessage(String key){
        return ctx.getApplication()
                  .getResourceBundle(ctx, "msg")
                  .getString(key);
    }
}
