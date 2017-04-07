package com.forumsite.service.impl;

import java.util.List;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.forumsite.data.UserRepository;
import com.forumsite.model.User;
import com.forumsite.service.UserProducer;

@Stateless
public class UserProducerImpl implements UserProducer {

    @Inject
    private UserRepository repo;
    
    @Override
    public Optional<User> getUser(String username) {
        return repo.getByName(username);
    }

    @Override
    public List<User> latest() {
        return repo.list();
    }

    @Override
    public List<User> findUsers(String username) {
        return repo.search(username);
    }

}
