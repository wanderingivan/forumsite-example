package com.forumsite.service;

import java.util.List;

import com.forumsite.model.ForumThread;

public interface ForumThreadProducer {

    public ForumThread getThread(String name);
    
    public List<ForumThread> latest();
    
    public List<ForumThread> search(String threadName);
    
    public List<ForumThread> category(String category);
}
