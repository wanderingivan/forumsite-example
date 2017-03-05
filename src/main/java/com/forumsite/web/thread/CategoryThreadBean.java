package com.forumsite.web.thread;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;

import com.forumsite.model.ForumThread;
import com.forumsite.service.ForumThreadProducer;

@Named("categoryBean")
@RequestScoped
public class CategoryThreadBean {

    @Inject
    private Logger logger;
    
    @Inject
    private ForumThreadProducer producer;
    
    private String category;
    
    private List<ForumThread> topics;
    
    public void load(){
        if(logger.isDebugEnabled()){
            logger.debug("Loading threads with category " + category);
        }
        topics = producer.category(category);
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<ForumThread> getTopics() {
        return topics;
    }
    
}
