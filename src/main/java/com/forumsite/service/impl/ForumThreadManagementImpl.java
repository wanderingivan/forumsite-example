package com.forumsite.service.impl;


import javax.ejb.Stateless;
import javax.inject.Inject;

import org.picketlink.Identity;
import org.picketlink.authorization.annotations.LoggedIn;
import org.picketlink.authorization.annotations.RolesAllowed;
import org.picketlink.http.AccessDeniedException;
import org.picketlink.idm.PermissionManager;
import org.picketlink.idm.model.basic.User;

import com.forumsite.data.ForumThreadRepository;
import com.forumsite.model.ForumThread;
import com.forumsite.service.ForumThreadManagement;

@Stateless
public class ForumThreadManagementImpl implements ForumThreadManagement {

    @Inject
    private Identity identity;
    
    @Inject
    private PermissionManager permissionManager;
    
    
    @Inject
    private ForumThreadRepository repo;

    @Override
    @LoggedIn
    public void saveThread(ForumThread t,String firstMessage) {
        User user = (User) identity.getAccount();
        repo.add(t,firstMessage,user.getLoginName());
        createAcl(t,user);
    }

    @Override
    public void updateThread(ForumThread t) {
        if(!this.identity.hasPermission(t, "update")){
            throw new AccessDeniedException(String.format("No permission for update on thread %s by user %s",t,identity.getAccount()
                                                                                                                       .getId()));
        }
        repo.update(t);
    }

    @Override
    @RolesAllowed({"admin"})
    public void deleteThread(long threadId) {
        repo.delete(threadId);
    }

    private void createAcl(ForumThread t,User user){
        permissionManager.grantPermission(user, t, "update, delete");
    }


}
