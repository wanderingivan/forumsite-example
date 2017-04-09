package com.forumsite.data;

import com.forumsite.model.Comment;

public interface CommentRepository extends Repository<Comment> {

    void add(Comment c, String threadName, String username);

    void update(long commentId, String message);
    
}
