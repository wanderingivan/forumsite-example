package com.forumsite.data;

import java.util.List;
import java.util.Optional;

import com.forumsite.model.User;

public interface UserRepository extends Repository<User> {
    
     Optional<User> getByName(String username);

     List<User> search(String username);

     boolean checkUsername(String username);

     boolean checkEmail(String email);

}
