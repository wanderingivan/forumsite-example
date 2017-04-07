package com.forumsite.service.impl;

import javax.ejb.Stateful;
import javax.inject.Inject;

import org.picketlink.Identity;
import org.picketlink.authorization.annotations.LoggedIn;
import org.picketlink.http.AccessDeniedException;
import org.picketlink.idm.PermissionManager;
import org.picketlink.idm.model.basic.User;

import com.forumsite.data.CommentRepository;
import com.forumsite.model.Comment;
import com.forumsite.service.CommentManagement;
import com.forumsite.util.AuthorizationChecker;

@Stateful
public class CommentManagementImpl implements CommentManagement {

    @Inject
    private AuthorizationChecker checker;
    
    @Inject
    private Identity identity;
    
    @Inject
    private CommentRepository repo;

    @Inject
    private PermissionManager permissionManager;
    
    @Override
    @LoggedIn
    public void addComment(Comment comment,String threadName) {
        User user = (User) identity.getAccount();
        repo.createComment(comment, threadName, user.getLoginName());
        createAcl(user,comment);
    }

    @Override
    public Comment getComment(long commentId) {
        return repo.retrieveComment(commentId);
    }

    @Override
    public void updateComment(Comment comment) {
        if(!checker.hasApplicationRole("admin") && !this.identity.hasPermission(comment, "update")){
            throw new AccessDeniedException(String.format("No permission for update for Comment %d by user %s",comment.getId(),identity.getAccount()
                                                                                                                             .getId()));
        }
        repo.updateComment(comment.getId(), comment.getMessage());
        
    }

    
    private void createAcl(User user,Comment comment){
        permissionManager.grantPermission(user, comment, "update, delete");
    } 
}
