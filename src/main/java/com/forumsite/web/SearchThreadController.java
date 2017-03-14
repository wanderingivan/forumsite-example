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
    
    private String category;
    
    private boolean useCategory = true;
    
    public String search(){
        getCategoryFromContext();
        if(logger.isDebugEnabled()){
            logger.debug("Search action querring for threads with name " + getQuery());
        }
        if(useCategory != false && (category != null && !category.isEmpty())){
            topics = service.search(getQuery(),getCategory());
        }else{
            topics = service.search(getQuery());
        }
        FacesMessage message = prepareMessage();
        ctx.addMessage("searchMessage", message);
        return "searchThreads";
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
    
    private void getCategoryFromContext(){
        setCategory(ctx.getExternalContext()
                       .getRequestParameterMap()
                       .get("searchForm:category"));
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isUseCategory() {
        return useCategory;
    }

    public void setUseCategory(boolean useCategory) {
        this.useCategory = useCategory;
    }
    
}
