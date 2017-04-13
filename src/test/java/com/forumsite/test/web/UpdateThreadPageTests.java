package com.forumsite.test.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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
public class UpdateThreadPageTests extends AbstractWebPageTests {

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
        login.loginIfNotAuthenticated("username2", "password");
        browser.get(deploymentUrl.toExternalForm() + "thread/editThread.jsf?threadname=threadname1");
        assertEquals("Editing threadname1",browser.getTitle().trim());
        editPage.edit("threadname11", "category2");
        assertTrue("The thread was not updated",browser.getTitle().trim().equals("threadname11"));
        assertEquals("threadname11",threadPage.getThreadname());
        assertEquals("Category2",threadPage.getCategory());
    }
    
    @Test
    public void editThreadInputMessagesTest(@InitialPage LoginPage login){
        login.loginIfNotAuthenticated("username2", "password");
        browser.get(deploymentUrl.toExternalForm() + "thread/editThread.jsf?threadname=threadname1");
        assertEquals("Editing threadname1",browser.getTitle().trim());
        editPage.edit("", "cat");
        assertFalse("There are no error messages for threadname", editPage.getThreadNameError().getText().isEmpty());
        assertFalse("There are no error messages for category", editPage.getCategoryError().getText().isEmpty());
    }
    
    @Test
    public void editThreadDuplicateThreadnameError(){
        fail("To be defined");
    }
    
    @Test
    public void editThreadAclAccessDeniedError(@InitialPage LoginPage login){
        login.loginIfNotAuthenticated("username2", "password");
        browser.get(deploymentUrl.toExternalForm() + "thread/editThread.jsf?threadname=threadname5");
        assertEquals("Editing threadname5",browser.getTitle().trim());
        editPage.edit("threadname10", "category2");
        ePage.assertOnAccessDeniedPage();
    }    
    
    @Test
    public void editThreadAccessDeniedLogin(@InitialPage LoginPage login){
        login.logoutIfAuthenticated();
        browser.get(deploymentUrl.toExternalForm() + "thread/editThread.jsf?threadname=threadname5");        
        assertEquals("Login",browser.getTitle().trim());
        assertTrue(login.assertOnLoginPage());
    }
}