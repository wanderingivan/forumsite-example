package com.forumsite.service.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.forumsite.data.ForumThreadRepository;
import com.forumsite.model.ForumThread;
import com.forumsite.service.ForumThreadManagement;

@Stateless
public class ForumThreadManagementImpl implements ForumThreadManagement {

    @Inject
    private ForumThreadRepository repo;

    @Override
    public void saveThread(ForumThread t) {
        repo.save(t);
    }

    @Override
    public void updateThread(ForumThread t) {
        repo.update(t);
    }

    @Override
    public void deleteThread(long threadId) {
        repo.delete(threadId);
    }

}
