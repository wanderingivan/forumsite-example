package com.forumsite.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.forumsite.data.CommentRepository;
import com.forumsite.model.Comment;
import com.forumsite.service.CommentProducer;

@Stateless
public class CommentProducerImpl implements CommentProducer {

    @Inject
    private CommentRepository repo;
    
    @Override
    public List<Comment> getCommentsForThread(String threadName) {
        return repo.getForThread(threadName);
    }

}
