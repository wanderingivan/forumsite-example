package com.forumsite.service;

import java.util.List;
import java.util.Optional;

import com.forumsite.model.User;

public interface UserProducer {

    public Optional<User> getUser(String username);
    
    public List<User> latest();
    
    public List<User> findUsers(String username);

}
