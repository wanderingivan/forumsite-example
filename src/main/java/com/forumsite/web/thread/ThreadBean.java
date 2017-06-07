package com.forumsite.web.thread;


import java.util.List;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.forumsite.model.Comment;
import com.forumsite.model.ForumThread;
import com.forumsite.service.CommentProducer;
import com.forumsite.util.HitResolver;
import com.forumsite.web.AbstractController;

@Model
public class ThreadBean extends AbstractController {

    @Inject
    private Logger logger;
    
    @Inject
    private CommentProducer producer;
    
    @Inject
    private HitResolver resolver;
    
    private final int DEFAULT_OFFSET = 0,COMMENTS_PER_PAGE=10;
    
    private int fromComment;
    
    private String threadName;
    
    private ForumThread topic;
    
    private boolean fromLast;
    
    public void load(){
        if(logger.isDebugEnabled()){
            logger.debug("Loading thread with name " + threadName);
        }
        List<Comment> comments= producer.getCommentsForThread(threadName);
        if(comments.size() < 1){
            ctx.addMessage(null, new FacesMessage(getMessage("no_thread") + threadName));
            return;
        }
        topic = comments.get(0).getThread();
        topic.setComments(comments);
        resolver.resolve(topic);
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
        if(fromLast){
            int first = topic.getComments().size() - COMMENTS_PER_PAGE;
            return first < 0 ? 0 : first; 
        }
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
    
    public void setFromLast(boolean fromLast){
        this.fromLast = fromLast;
    }
    
    public boolean getFromLast(){
        return this.fromLast;
    }
    
}
