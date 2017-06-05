package com.forumsite.web.user;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import org.picketlink.Identity;
import org.picketlink.http.AccessDeniedException;

import com.forumsite.service.UserManagement;
import com.forumsite.web.AbstractController;

@Model
public class ChangePasswordController extends AbstractController {

    @Inject
    private UserManagement umgmt;
    
    @Inject
    private Identity identity;
    
    private String newPassword,
                   oldPassword;
    
    public String changePassword() {
        if(!identity.isLoggedIn()){ throw new AccessDeniedException("Tried to change password without auth"); }
        
        if(umgmt.checkPassword(oldPassword)){
            umgmt.changePassword(newPassword);
            identity.logout();
            return "/login.jsf";
        }
        ctx.addMessage(null, new FacesMessage(getMessage("invalid_password")));
        return "";
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

}
