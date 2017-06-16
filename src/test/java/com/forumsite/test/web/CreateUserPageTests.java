package com.forumsite.test.web;


import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.graphene.page.InitialPage;
import org.jboss.arquillian.graphene.page.Page;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.forumsite.test.util.Deployments;
import com.forumsite.test.web.page.CreateUserPage;
import com.forumsite.test.web.page.ErrorPage;
import com.forumsite.test.web.page.LoginPage;
import com.forumsite.test.web.page.ShowUserPage;

import static org.junit.Assert.*;



@RunWith(Arquillian.class)
public class CreateUserPageTests extends AbstractWebPageTest {

    
    @Deployment(testable = false)
    public static WebArchive createDeployment(){
        return Deployments.projectWar();
    }
    
    @Page
    CreateUserPage cPage;
    
    @Page
    ShowUserPage userPage;
    
    @Page
    ErrorPage ePage;
    
    @Test
    public void createUserTest(@InitialPage LoginPage login){
        login.logoutIfAuthenticated();
        loadPage("user/newUser.jsf");      
        cPage.assertCreateUser("username5", "password", "email@email.com", "empty");
        assertEquals("username5's Profile Page",browser.getTitle().trim());
        assertEquals("username5",userPage.getUsername());
        assertEquals("empty",userPage.getDescription());
    }
    
    @Test
    public void createUserInputMessagesTest(@InitialPage CreateUserPage cPage){
        cPage.assertCreateUser("use", "pas", "email", "empty");
        assertFalse(cPage.getUsernameError().getText().isEmpty());
        assertFalse(cPage.getPasswordError().getText().isEmpty());
        assertFalse(cPage.getEmailError().getText().isEmpty());
    }
    
    @Test
    public void createUserDuplicateUsernameExceptionTest(@InitialPage CreateUserPage cPage){
        fail("To be defined");
    }
    
    @Test
    public void createUserDuplicateEmailTest(@InitialPage CreateUserPage cPage){
        fail("To be defined");
    }
    
    @Test
    public void createUserAccessDeniedTest(@InitialPage LoginPage login){
        login.loginIfNotAuthenticated("username2", "password");
        loadPage("user/newUser.jsf");
        cPage.assertCreateUser("username7", "password", "email@email7.com", "empty");
        ePage.assertOnAccessDeniedPage();
    }
    
}
