package com.forumsite.service.impl;

import java.util.Optional;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.Part;

import org.picketlink.idm.credential.Password;
import org.picketlink.idm.model.basic.BasicModel;
import org.picketlink.authorization.annotations.LoggedIn;
import org.picketlink.authorization.annotations.RequiresPermission;
import org.picketlink.authorization.annotations.Restrict;
import org.picketlink.authorization.annotations.RolesAllowed;
import org.picketlink.idm.IdentityManager;
import org.picketlink.idm.PermissionManager;
import org.picketlink.idm.RelationshipManager;

import com.forumsite.data.UserRepository;
import com.forumsite.model.User;
import com.forumsite.service.ImageService;
import com.forumsite.service.UserManagement;

@Stateless
public class UserManagementImpl implements UserManagement {

    @Inject
    private IdentityManager idm;
    
    @Inject
    private PermissionManager permissionManager;
    
    @Inject
    private RelationshipManager relManager;
    
    @Inject
    private UserRepository repo;
    
    @Inject
    private ImageService imageService;
    
    private String placeholder;
    
    @Override
    @Restrict("#{!isLoggedIn()}")
    public void saveUser(User u,Optional<Part> image) throws Exception{
        String imageName = placeholder;
        if(image.isPresent()){ imageName = imageService.saveImage(image.get()); }
        u.setImageName(imageName);
        repo.save(u);
        updatePricketlink(u);
    }

    @Override
    @LoggedIn
    @RequiresPermission(resourceClass=User.class,operation="update")
    public void updateUser(User u,Optional<Part> image) throws Exception{
        if(image.isPresent()){ 
            String imageName = imageService.saveImage(image.get()); 
            u.setImageName(imageName);
        }
        repo.update(u);
    }

    @Override
    @RolesAllowed("admin")
    public void deleteUser(long userId) {
        repo.delete(userId);
    }

    private void updatePricketlink(User u){
        org.picketlink.idm.model.basic.User user = new org.picketlink.idm.model.basic.User(u.getUsername());
        user.setEmail(u.getEmail());
        idm.add(user);
        idm.updateCredential(user, new Password(u.getPassword()));
        permissionManager.grantPermission(user, u, "update");
        BasicModel.grantRole(relManager, user, BasicModel.getRole(idm, "user"));
    }
    
}
