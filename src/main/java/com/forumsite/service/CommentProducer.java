package com.forumsite.service;

import java.util.List;

import com.forumsite.model.Comment;

public interface CommentProducer {

    List<Comment> getCommentsForThread(String threadName);

}
