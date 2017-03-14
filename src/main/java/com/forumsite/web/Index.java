package com.forumsite.web;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import com.forumsite.model.ForumThread;
import com.forumsite.service.ForumThreadProducer;

@Model
public class Index {

    @Inject
    private ForumThreadProducer service;
    
    private List<ForumThread> threads;
    
    @PostConstruct
    public void init(){
        threads = service.index();
    }
    
    public List<ForumThread> getThreads(){
        return this.threads;
    }
    
}
