package com.forumsite.service;

import com.forumsite.model.Comment;

public interface CommentManagement {

    void addComment(Comment comment,String threadName);

}
