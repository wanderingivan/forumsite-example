package com.forumsite.data;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.forumsite.model.ForumThread;

public interface ForumThreadRepository extends Repository<ForumThread> {
    
    
    Optional<ForumThread> getByName(String threadName);
    
    void add(ForumThread thread,String firstMessage,String username);
    
    void update(ForumThread thread);
    
    List<ForumThread> latest();
    
    List<ForumThread> search(String threadName);
    
    List<ForumThread> search(String threadName, String category);
    
    List<ForumThread> getCategory(String category);

    void updateHits(Map<Long, Long> hits);

    boolean checkName(String threadname);
}
