package com.forumsite.web;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.forumsite.model.ForumThread;
import com.forumsite.service.ForumThreadProducer;

@Model
public class SearchThreadController {

    @Inject
    private FacesContext ctx;
    
    @Inject
    private Logger logger;
    
    @Inject
    private ForumThreadProducer service;
    
    private List<ForumThread> topics;
    
    private String query;
    
    public String search(){
        if(logger.isDebugEnabled()){
            logger.debug("Search action querring for threads with name " + getQuery());
        }
        topics = service.search(getQuery());
        ctx.addMessage("searchMessage", prepareMessage());
        return "thread/searchThreads";
    }
    
    private FacesMessage prepareMessage(){
        if(topics.size() < 1){ 
            return new FacesMessage(getMessage("no_results") + getQuery());
        }else{ 
            return new FacesMessage(getMessage("threads_matching") + getQuery());
        }
    }
    
    private String getMessage(String key){
        return ctx.getApplication()
                  .getResourceBundle(ctx, "msg")
                  .getString(key);
    }

    public List<ForumThread> getTopics(){
        return this.topics;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
    
}
