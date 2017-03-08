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
import com.forumsite.test.web.page.ShowUserPage;

import static org.junit.Assert.*;



@RunWith(Arquillian.class)
public class CreateUserPageTest extends AbstractWebPageTest {

    
    @Deployment(testable = false)
    public static WebArchive createDeployment(){
        return Deployments.projectWar();
    }
    
    @Page
    ShowUserPage userPage;
    
    @Test
    public void createUserTest(@InitialPage CreateUserPage cPage){
        cPage.assertCreateUser("username4", "password", "email@email.com", "empty");
        assertEquals("username4Profile Page",browser.getTitle().trim());
        assertEquals("username4",userPage.getUsername());
        assertEquals("email@email.com",userPage.getEmail());
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
    public void createUserDuplicateUsernameError(@InitialPage CreateUserPage cPage){
        fail("To be defined");
    }
    
    @Test
    public void createUserDuplicateEmailError(@InitialPage CreateUserPage cPage){
        fail("To be defined");
    }
    
    @Test
    public void createUserAccessDeniedError(@InitialPage CreateUserPage cPage){
        fail("To be defined");
    }
    
}
