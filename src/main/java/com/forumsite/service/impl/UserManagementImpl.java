package com.forumsite.service.impl;

import java.util.Optional;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.Part;

import com.forumsite.data.UserRepository;
import com.forumsite.model.User;
import com.forumsite.service.ImageService;
import com.forumsite.service.UserManagement;

@Stateless
public class UserManagementImpl implements UserManagement {

    @Inject
    private UserRepository repo;
    
    @Inject
    private ImageService imageService;
    
    private String placeholder;
    
    @Override
    public void saveUser(User u,Optional<Part> image) throws Exception{
        String imageName = placeholder;
        if(image.isPresent()){ imageName = imageService.saveImage(image.get()); }
        u.setImageName(imageName);
        repo.save(u);
    }

    @Override
    public void updateUser(User u,Optional<Part> image) throws Exception{
        if(image.isPresent()){ 
            String imageName = imageService.saveImage(image.get()); 
            u.setImageName(imageName);
        }
        repo.update(u);
    }

    @Override
    public void deleteUser(long userId) {
        repo.delete(userId);
    }

}
