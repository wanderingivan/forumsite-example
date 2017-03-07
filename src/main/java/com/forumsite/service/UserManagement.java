package com.forumsite.service;

import java.util.Optional;

import javax.servlet.http.Part;

import com.forumsite.model.User;

public interface UserManagement {

    public void saveUser(User u, Optional<Part> image) throws Exception;
    
    public void updateUser(User u, Optional<Part> image) throws Exception;
    
    public void deleteUser(long userId);
    
}
