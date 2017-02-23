package com.forumsite.data;

import java.util.List;

import com.forumsite.model.User;

public interface UserRepository {

    public User findUser(long id);
    
    public User getUserByName(String username);
    
    public List<User> listUsers();

    public List<User> searchUsers(String username);
    
    public void save(User user);
    
    public void update(User user);
    
    public void delete(long userId);

}
