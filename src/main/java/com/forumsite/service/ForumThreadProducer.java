package com.forumsite.service;

import java.util.List;
import java.util.Optional;

import com.forumsite.model.ForumThread;

public interface ForumThreadProducer {

    public Optional<ForumThread> getThread(String name);
    
    public List<ForumThread> index();
    
    public List<ForumThread> search(String threadName);
    
    public List<ForumThread> search(String threadName, String category);
    
    public List<ForumThread> category(String category);
    

    public List<ForumThread> latest();
}
