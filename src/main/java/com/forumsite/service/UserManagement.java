package com.forumsite.service;

import java.util.Optional;

import javax.servlet.http.Part;

import com.forumsite.model.User;

public interface UserManagement {

    void saveUser(User u, Optional<Part> image) throws Exception;
    
    void updateUser(User u, Optional<Part> image) throws Exception;
    
    void deleteUser(long userId);

    void changePassword(String newPassword);
    
    boolean checkPassword(String password);

    boolean isUsernameAvailable(String username);

    boolean isEmailAvailable(String email);
    
}
