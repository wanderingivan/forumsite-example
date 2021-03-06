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
public class CreateThreadPageTests extends AbstractWebPageTest{
    
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
    public void createThreadTest(@InitialPage LoginPage login){ 
        login.loginIfNotAuthenticated("username2", "password");
        loadPage("thread/newThread.jsf");
        cPage.createThread("threadname4", "First Person Shooter","message");
        assertEquals("threadname4",browser.getTitle().trim());
        assertEquals("threadname4",threadPage.getThreadname());
        assertEquals("First Person Shooter",threadPage.getCategory());
        assertTrue("Comment was not saved",threadPage.commentExists());

    }
    
    @Test
    public void createThreadInputMessagesTest(@InitialPage LoginPage login){
        login.loginIfNotAuthenticated("username2", "password");
        loadPage("thread/newThread.jsf");
        cPage.createThread("thre", "First Person Shooter", "");
        assertEquals("We're not on input page","New Thread",browser.getTitle().trim());
        assertFalse("Threadname error messages are missing",cPage.getThreadNameError().isEmpty());
        assertFalse("Message error messages are missing",cPage.getMessageError().isEmpty());
    }
    
    @Test
    public void createThreadDuplicateThreadnameError(@InitialPage LoginPage login){
        login.loginIfNotAuthenticated("username2", "password");
        loadPage("thread/newThread.jsf");
        cPage.createThread("Red Alert", "First Person Shooter","message");
        assertEquals("We're not on input page","New Thread",browser.getTitle().trim());
        assertFalse("Threadname duplicate error messages are missing",cPage.getThreadNameError().isEmpty());
    }
    
    @Test
    public void createThreadAccessDeniedLogin(@InitialPage LoginPage login){
        login.logoutIfAuthenticated();
        loadPage("thread/newThread.jsf");
        assertEquals("Login",browser.getTitle().trim());
        assertTrue(login.assertOnLoginPage());
    }

}
