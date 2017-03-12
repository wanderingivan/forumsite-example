package com.forumsite.data;

import java.util.List;

import com.forumsite.model.ForumThread;

public interface ForumThreadRepository {
    
    public ForumThread findThread(long id);
    
    public ForumThread getThreadByName(String threadName);
    
    public void save(ForumThread thread,String firstMessage,String username);
    
    public void update(ForumThread thread);
    
    public List<ForumThread> list();
    
    public List<ForumThread> searchThreads(String threadName);
    
    public List<ForumThread> loadCategory(String category);
    
    public void delete(long id);

}
