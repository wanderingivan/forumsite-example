package com.forumsite.service.impl;

import javax.ejb.Stateful;
import javax.inject.Inject;

import org.picketlink.Identity;
import org.picketlink.authorization.annotations.LoggedIn;
import org.picketlink.idm.model.basic.User;

import com.forumsite.data.CommentRepository;
import com.forumsite.model.Comment;
import com.forumsite.service.CommentManagement;

@Stateful
public class CommentManagementImpl implements CommentManagement {

    @Inject
    private Identity identity;
    
    @Inject
    private CommentRepository repo;
    
    @Override
    @LoggedIn
    public void addComment(Comment comment,String threadName) {
        User user = (User) identity.getAccount();
        repo.createComment(comment, threadName, user.getLoginName());
    }

}
