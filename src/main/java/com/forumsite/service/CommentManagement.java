package com.forumsite.service;

import java.util.Optional;

import com.forumsite.model.Comment;

public interface CommentManagement {

    Optional<Comment> getComment(long commentId);
    
    void addComment(Comment comment,String threadName);

    void updateComment(Comment comment);

}
