package com.forumsite.data;

import com.forumsite.model.Comment;

public interface CommentRepository {

    void createComment(Comment c, long thread_id, String username);

    Comment retrieveComment(long id);

    void updateComment(long commentId, String message);

    void delete(long commentId);

}
