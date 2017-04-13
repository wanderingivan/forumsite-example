package com.forumsite.web.thread;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.forumsite.model.ForumThread;
import com.forumsite.service.ForumThreadProducer;
import com.forumsite.web.AbstractController;

@Model
public class SearchThreadController extends AbstractController{

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
