package com.forumsite.web;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import com.forumsite.model.ForumThread;
import com.forumsite.service.ForumThreadProducer;
import com.forumsite.util.HitResolver;

@Model
public class Index {

    @Inject
    private ForumThreadProducer service;
    
    @Inject
    private HitResolver resolver;
    
    private List<ForumThread> threads;
    
    @PostConstruct
    public void init(){
        threads = service.index();
        resolver.resolve(threads);
    }
    
    public List<ForumThread> getThreads(){
        return this.threads;
    }
    
}
