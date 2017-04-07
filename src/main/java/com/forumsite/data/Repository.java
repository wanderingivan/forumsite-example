package com.forumsite.data;

import java.util.List;
import java.util.Optional;

import com.forumsite.model.Identity;

public interface Repository<T extends Identity> {
    
    default Optional<T> get(long id){
        return list().stream()
                     .filter(entity -> entity.getId() == id)
                     .findAny();
    }
    
    void add(T t);
    
    void update(T t);
    
    void delete(long id);
    
    List<T> list();


}
