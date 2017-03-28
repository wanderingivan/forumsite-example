package com.forumsite.service.impl;

import java.util.List;

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
    public ForumThread getThread(String name) {
        return repo.getThreadByName(name);
    }

    @Override
    public List<ForumThread> index() {
        return repo.list();
    }

    @Override
    public List<ForumThread> search(String threadName) {
        return repo.searchThreads(threadName);
    }

    @Override
    public List<ForumThread> search(String threadName, String category) {
        return repo.searchThreads(threadName,category);
    }
    
    @Override
    public List<ForumThread> category(String category) {
        return repo.loadCategory(category);
    }

    @Produces
    @Named
    @RequestScoped
    @Override
    public List<ForumThread> latest(){
        return repo.latest();
    }
}
