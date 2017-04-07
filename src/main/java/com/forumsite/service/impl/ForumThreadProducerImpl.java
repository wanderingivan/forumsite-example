package com.forumsite.service.impl;

import java.util.List;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import com.forumsite.data.ForumThreadRepository;
import com.forumsite.model.ForumThread;
import com.forumsite.service.ForumThreadProducer;

@Named("forumThreadProvider")
@Stateless
public class ForumThreadProducerImpl implements ForumThreadProducer {

    @Inject
    private ForumThreadRepository repo;
    
    @Override
    public Optional<ForumThread> getThread(String name) {
        return repo.getByName(name);
    }

    @Override
    public List<ForumThread> index() {
        return repo.list();
    }

    @Override
    public List<ForumThread> search(String threadName) {
        return repo.search(threadName);
    }

    @Override
    public List<ForumThread> search(String threadName, String category) {
        return repo.search(threadName,category);
    }
    
    @Override
    public List<ForumThread> category(String category) {
        return repo.getCategory(category);
    }

    @Produces
    @Named
    @RequestScoped
    @Override
    public List<ForumThread> latest(){
        return repo.latest();
    }
}
