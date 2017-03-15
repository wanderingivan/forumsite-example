package com.forumsite.service;

import com.forumsite.model.Comment;

public interface CommentManagement {

    Comment getComment(long commentId);
    
    void addComment(Comment comment,String threadName);

    void updateComment(Comment comment);

}
