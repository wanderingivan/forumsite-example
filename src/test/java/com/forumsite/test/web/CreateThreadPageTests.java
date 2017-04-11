package com.forumsite.test.web;

import static org.junit.Assert.*;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.graphene.page.InitialPage;
import org.jboss.arquillian.graphene.page.Page;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.forumsite.test.util.Deployments;
import com.forumsite.test.web.page.CreateThreadPage;
import com.forumsite.test.web.page.ErrorPage;
import com.forumsite.test.web.page.LoginPage;
import com.forumsite.test.web.page.ShowThreadPage;


@RunWith(Arquillian.class)
public class CreateThreadPageTests extends AbstractWebPageTests{
    
    @Deployment(testable=false)
    public static WebArchive createDeployment(){
        return Deployments.projectWar();
    }
    
    @Page
    ShowThreadPage threadPage;
    
    @Page
    CreateThreadPage cPage;
    
    @Page
    ErrorPage ePage;
    
    @Test
    public void createThreadTest(@InitialPage LoginPage login){ // NOTE right now this is a very short test that checks if the user has permission to create,
        login.loginIfNotAuthenticated("username2", "password"); // it should check that the entity is persisted and presented properly
        browser.get(deploymentUrl.toExternalForm() + "thread/newThread.jsf");
        cPage.createThread("threadname4", "category1","message");
        assertEquals("threadname4",browser.getTitle().trim());
        assertEquals("threadname4",threadPage.getThreadname());
        assertEquals("category1",threadPage.getCategory());
        assertTrue("Comment was not saved",threadPage.commentExists());
        //assertEquals("message",threadPage.getFirstComment());

    }
    
    @Test
    public void createThreadInputMessagesTest(@InitialPage LoginPage login){
        login.loginIfNotAuthenticated("username2", "password");
        browser.get(deploymentUrl.toExternalForm() + "thread/newThread.jsf");
        cPage.createThread("thre", "cat", "");
        assertEquals("We're not on input page","New Thread",browser.getTitle().trim());
        assertFalse("Threadname error messages are missing",cPage.getThreadNameError().getText().isEmpty());
        assertFalse("Category error messages are missing",cPage.getCategoryError().getText().isEmpty());
        assertFalse("Message error messages are missing",cPage.getMessageError().getText().isEmpty());
    }
    
    @Test
    public void createThreadDuplicateThreadnameError(@InitialPage CreateThreadPage cPage){
        fail("To be defined");
    }
    
    @Test
    public void createThreadAccessDeniedLogin(@InitialPage LoginPage login){
        login.logoutIfAuthenticated();
        browser.get(deploymentUrl.toExternalForm() + "thread/newThread.jsf");
        assertEquals("Login",browser.getTitle().trim());
        assertTrue(login.assertOnLoginPage());
    }

}
