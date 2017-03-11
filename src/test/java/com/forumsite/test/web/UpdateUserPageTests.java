package com.forumsite.test.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.graphene.page.InitialPage;
import org.jboss.arquillian.graphene.page.Page;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.forumsite.test.util.Deployments;
import com.forumsite.test.web.page.EditUserPage;
import com.forumsite.test.web.page.ErrorPage;
import com.forumsite.test.web.page.LoginPage;
import com.forumsite.test.web.page.ShowUserPage;

@RunWith(Arquillian.class)
public class UpdateUserPageTests extends AbstractWebPageTests {

    @Deployment(testable=false)
    public static WebArchive createDeployment(){
        return Deployments.projectWar();
    }
    
    @Page
    ShowUserPage userPage;
    
    @Page
    EditUserPage editPage;
    
    @Page
    ErrorPage ePage;
    
    @Test
    public void editUserTest(@InitialPage LoginPage login){
        login.loginIfNotAuthenticated("username2", "password");
        browser.get(deploymentUrl.toExternalForm() + "editUser.jsf?username=username2");
        assertEquals("Editing username2",browser.getTitle().trim());
        editPage.editUser("username123", "email@email23.com", "empty");

        assertEquals("username123's Profile Page",browser.getTitle().trim());
        assertEquals("username123",userPage.getUsername());
        assertEquals("email@email23.com",userPage.getEmail());
        assertEquals("empty",userPage.getDescription());
    }
    
    @Test
    public void editUserInputMessagesTest(@InitialPage LoginPage login){
        login.loginIfNotAuthenticated("username2", "password");
        browser.get(deploymentUrl.toExternalForm() + "editUser.jsf?username=username2");
        assertEquals("Editing username2",browser.getTitle().trim());
        editPage.editUser("use", "email", "empty");
        assertFalse(editPage.getUsernameError().getText().isEmpty());
        assertFalse(editPage.getEmailError().getText().isEmpty());
    }
    
    @Test
    public void editUserAclAccessDeniedTest(@InitialPage LoginPage login){
        login.loginIfNotAuthenticated("username2", "password");
        browser.get(deploymentUrl.toExternalForm() + "editUser.jsf?username=username3");
        assertEquals("Editing username3",browser.getTitle().trim());
        editPage.editUser("username123", "email@email23.com", "empty");
        ePage.assertOnAccessDeniedPage();
    }
    
}
