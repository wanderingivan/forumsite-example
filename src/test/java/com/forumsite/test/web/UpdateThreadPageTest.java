package com.forumsite.test.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.graphene.page.Page;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.forumsite.test.util.Deployments;
import com.forumsite.test.web.page.EditThreadPage;
import com.forumsite.test.web.page.ShowThreadPage;

@RunWith(Arquillian.class)
public class UpdateThreadPageTest extends AbstractWebPageTest {

    @Deployment(testable = false)
    public static WebArchive createDeployment(){
        return Deployments.projectWar();
    }
    
    @Page
    ShowThreadPage threadPage;
    
    @Page
    EditThreadPage editPage;
    
    @Test
    public void editThreadTest(){
        browser.get(deploymentUrl.toExternalForm() + "editThread.jsf?threadname=threadname1");
        assertEquals("Editing threadname1",browser.getTitle().trim());
        editPage.edit("threadname10", "category2");

        assertTrue("The thread was not saved",browser.getTitle().trim().equals("threadname10"));
        assertEquals("username123",threadPage.getThreadname());
        assertEquals("email@emal23.com",threadPage.getCategory());
    }
    
    @Test
    public void editThreadInputMessagesTest(){
        browser.get(deploymentUrl.toExternalForm() + "editThread.jsf?threadname=threadname1");
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
    public void editThreadAccessDeniedError(){
        fail("To be defined");
    }    
    
}