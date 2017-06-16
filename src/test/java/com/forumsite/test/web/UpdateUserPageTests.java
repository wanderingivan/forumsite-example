package com.forumsite.test.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.graphene.page.InitialPage;
import org.jboss.arquillian.graphene.page.Page;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.forumsite.test.util.Deployments;
import com.forumsite.test.web.page.EditUserPage;
import com.forumsite.test.web.page.ErrorPage;
import com.forumsite.test.web.page.LoginPage;
import com.forumsite.test.web.page.ShowUserPage;

@RunWith(Arquillian.class)
public class UpdateUserPageTests extends AbstractWebPageTest {

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
    @InSequence(1)
    public void editUserInputMessagesTest(@InitialPage LoginPage login){
        login.loginIfNotAuthenticated("username2", "password");
        loadPage("user/editUser.jsf?username=username2");
        assertEquals("Editing username2",browser.getTitle().trim());
        editPage.editUser("use", "email", "empty");
        assertFalse(editPage.getUsernameError().isEmpty());
        assertFalse(editPage.getEmailError().isEmpty());
    }
    
    @Test
    @InSequence(2)
    public void editUserTest(@InitialPage LoginPage login){
        login.loginIfNotAuthenticated("username2", "password");
        loadPage("user/editUser.jsf?username=username2");
        assertEquals("Editing username2",browser.getTitle().trim());
        editPage.editUser("username123", "email@email23.com", "empty");

        assertEquals("username123's Profile Page",browser.getTitle().trim());
        assertEquals("username123",userPage.getUsername());
        assertEquals("empty",userPage.getDescription());
    }
    
    @Test
    @InSequence(3)
    public void editUserAclAccessDeniedTest(@InitialPage LoginPage login){
        login.loginIfNotAuthenticated("username2", "password");
        loadPage("user/editUser.jsf?username=username3");
        assertEquals("Editing username3",browser.getTitle().trim());
        editPage.editUser("username123", "email@email23.com", "empty");
        ePage.assertOnAccessDeniedPage();
    }
    
    @Test
    @InSequence(4)
    public void editUserAccessDeniedLoginTest(@InitialPage LoginPage login){
        login.logoutIfAuthenticated();
        loadPage("user/editUser.jsf?username=username3");
        assertEquals("Login",browser.getTitle().trim());
        assertTrue(login.assertOnLoginPage());
    }
    
}
