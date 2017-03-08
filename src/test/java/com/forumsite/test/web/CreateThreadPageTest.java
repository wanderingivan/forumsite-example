package com.forumsite.test.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.graphene.page.InitialPage;
import org.jboss.arquillian.graphene.page.Page;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.forumsite.test.util.Deployments;
import com.forumsite.test.web.page.CreateThreadPage;
import com.forumsite.test.web.page.ShowThreadPage;


@RunWith(Arquillian.class)
public class CreateThreadPageTest extends AbstractWebPageTest{
    
    @Deployment(testable=false)
    public static WebArchive createDeployment(){
        return Deployments.projectWar();
    }
    
    @Page
    ShowThreadPage threadPage;
    
    @Test
    public void createThreadTest(@InitialPage CreateThreadPage cPage){
        cPage.createThread("threadname4", "category","message");
        assertEquals("threadname4",browser.getTitle().trim());

    }
    
    @Test
    public void createThreadInputMessagesTest(@InitialPage CreateThreadPage cPage){
        cPage.createThread("thre", "cat", "");
        assertFalse("Threadname error messages are missing",cPage.getThreadNameError().getText().isEmpty());
        assertFalse("Category error messages are missing",cPage.getCategoryError().getText().isEmpty());
        assertFalse("Message error messages are missing",cPage.getMessageError().getText().isEmpty());
    }
    
    @Test
    public void createUserDuplicateThreadnameError(@InitialPage CreateThreadPage cPage){
        fail("To be defined");
    }
    
    @Test
    public void createUserThreadDeniedError(@InitialPage CreateThreadPage cPage){
        fail("To be defined");
    }
}
