package com.forumsite.service;

import java.util.Optional;

import javax.servlet.http.Part;

import com.forumsite.model.User;

public interface UserManagement {

    //@Restrict("#{!isLoggedIn()}")
    public void saveUser(User u, Optional<Part> image) throws Exception;
    
    //@Restrict("#{hasPermission('user','write') or hasRole('Admin')}")
    public void updateUser(User u, Optional<Part> image) throws Exception;
    
    //@Restrict("#{hasRole('Admin')}")
    public void deleteUser(long userId);
    
}
