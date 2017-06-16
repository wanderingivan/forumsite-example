package com.forumsite.test.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.graphene.page.InitialPage;
import org.jboss.arquillian.graphene.page.Page;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.forumsite.test.util.Deployments;
import com.forumsite.test.web.page.EditThreadPage;
import com.forumsite.test.web.page.ErrorPage;
import com.forumsite.test.web.page.LoginPage;
import com.forumsite.test.web.page.ShowThreadPage;

@RunWith(Arquillian.class)
public class UpdateThreadPageTests extends AbstractWebPageTest {

    @Deployment(testable = false)
    public static WebArchive createDeployment(){
        return Deployments.projectWar();
    }
    
    @Page
    ShowThreadPage threadPage;
    
    @Page
    EditThreadPage editPage;
    
    @Page
    ErrorPage ePage;
    
    @Test
    public void editThreadTest(@InitialPage LoginPage login){
        login.logoutIfAuthenticated();
        loadPage("/login.jsf");
        login.login("username2", "password");
        loadPage("thread/editThread.jsf?threadname=Arma 3");
        assertEquals("Editing Arma 3",browser.getTitle().trim());
        editPage.edit("Arma 4", "Role Play Game");
        assertTrue("The thread was not updated",browser.getTitle().trim().equals("Arma 4"));
        assertEquals("Arma 4",threadPage.getThreadname());
        assertEquals("Role Play Game",threadPage.getCategory());
    }
    
    @Test
    public void editThreadInputMessagesTest(@InitialPage LoginPage login){
        login.loginIfNotAuthenticated("username2", "password");
        loadPage("thread/editThread.jsf?threadname=Arma 3");
        assertEquals("Editing Arma 3",browser.getTitle().trim());
        editPage.edit("", "Role Play Game");
        assertFalse("There are no error messages for threadname", editPage.getThreadNameError().isEmpty());
    }
    
    @Test
    public void editThreadAclAccessDeniedError(@InitialPage LoginPage login){
        login.logoutIfAuthenticated();
        loadPage("/login.jsf");
        login.login("username3", "password");
        loadPage("thread/editThread.jsf?threadname=Arma 3");
        assertEquals("Editing Arma 3",browser.getTitle().trim());
        editPage.edit("threadname10", "Role Play Game");
        ePage.assertOnAccessDeniedPage();
    }    
    
    @Test
    public void editThreadAccessDeniedLogin(@InitialPage LoginPage login){
        login.logoutIfAuthenticated();
        loadPage("thread/editThread.jsf?threadname=threadname5");        
        assertEquals("Login",browser.getTitle().trim());
        assertTrue(login.assertOnLoginPage());
    }
}