package com.forumsite.test.web;

import static org.junit.Assert.*;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.graphene.GrapheneElement;
import org.jboss.arquillian.graphene.GrapheneElementImpl;
import org.jboss.arquillian.graphene.page.InitialPage;
import org.jboss.arquillian.graphene.page.Page;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;

import com.forumsite.test.util.Deployments;
import com.forumsite.test.web.page.CreateCommentPage;
import com.forumsite.test.web.page.ErrorPage;
import com.forumsite.test.web.page.LoginPage;
import com.forumsite.test.web.page.ShowThreadPage;

@RunWith(Arquillian.class)
public class AddCommentPageTests extends AbstractWebPageTests {
    
    @Deployment(testable= false)
    public static WebArchive createDeployment(){
        return Deployments.projectWar();
    }
    
    @Page
    ShowThreadPage tPage;
    
    @Page
    CreateCommentPage cPage;
    
    @Page
    ErrorPage ePage;
    
    
    @Test
    public void testCreateComment(@InitialPage LoginPage login){
        login.loginIfNotAuthenticated("username2", "password");
        browser.get(deploymentUrl.toExternalForm() + "loadThread.jsf?threadname=threadname2");
        assertEquals("threadname2",browser.getTitle().trim());
        tPage.goToReply();
        assertEquals("threadname2 add comment",browser.getTitle().trim());
        cPage.sendComment("a new comment");
        GrapheneElement comment = new GrapheneElementImpl(browser.findElement(By.id("commentId0")));
        assertTrue("Did not find comment",comment.isPresent());
        assertEquals("a new comment",comment.getText().toString());
    }
    
    @Test
    public void testCreateCommentLoginError(@InitialPage LoginPage login){
        login.logoutIfAuthenticated();
        browser.get(deploymentUrl.toExternalForm() + "loadThread.jsf?threadname=threadname2");
        assertEquals("threadname2",browser.getTitle().trim());
        tPage.goToReply();
        assertEquals("threadname2 add comment",browser.getTitle().trim());
        cPage.sendComment("a new comment");
        ePage.assertOnAccessDeniedPage();
    }
    
    @Test
    public void testCreateCommentInputMessage(@InitialPage LoginPage login){
        login.loginIfNotAuthenticated("username2", "password");
        browser.get(deploymentUrl.toExternalForm() + "loadThread.jsf?threadname=threadname2");
        assertEquals("threadname2",browser.getTitle().trim());
        tPage.goToReply();
        assertEquals("threadname2 add comment",browser.getTitle().trim());
        cPage.sendComment("");
        assertFalse("There are no error messages for comment message", cPage.getMessageError().getText().isEmpty());
    }

}
