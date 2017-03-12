package com.forumsite.service;

import com.forumsite.model.ForumThread;

public interface ForumThreadManagement {
    
    public void saveThread(ForumThread t,String firstMessage);
    
    public void updateThread(ForumThread t);
    
    public void deleteThread(long threadId);
    
    
}
