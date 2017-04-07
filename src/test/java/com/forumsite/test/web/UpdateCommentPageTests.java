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
import com.forumsite.test.web.page.EditCommentPage;
import com.forumsite.test.web.page.ErrorPage;
import com.forumsite.test.web.page.LoginPage;

@RunWith(Arquillian.class)
public class UpdateCommentPageTests extends AbstractWebPageTests {

    
    @Deployment(testable=false)
    public static WebArchive createDeployment(){
        return Deployments.projectWar();
    }
    
    @Page
    EditCommentPage ePage;
    
    @Page
    ErrorPage errorPage;
    
    @Test
    public void testUpdateComment(@InitialPage LoginPage login){
        login.loginIfNotAuthenticated("username2", "password");
        browser.get(deploymentUrl.toExternalForm()+ "editComment.jsf?commentId=112");
        assertEquals("Editing comment 112",browser.getTitle().trim());
        ePage.editComment("A new message");
        
    }
    
    @Test
    public void testUpdateCommentAccessDeniedFromAcl(@InitialPage LoginPage login){
        login.loginIfNotAuthenticated("username3", "password");
        browser.get(deploymentUrl.toExternalForm()+ "editComment.jsf?commentId=112");
        assertEquals("Editing comment 112",browser.getTitle().trim());
        ePage.editComment("A new message");
        errorPage.assertOnAccessDeniedPage();
    }
    
    @Test
    public void testUpdateCommentAccessDeniedLogin(@InitialPage LoginPage login){
        login.logoutIfAuthenticated();
        browser.get(deploymentUrl.toExternalForm()+ "editComment.jsf?commentId=112");
        assertEquals("Login",browser.getTitle().trim());
        assertTrue(login.assertOnLoginPage());
    }
}
