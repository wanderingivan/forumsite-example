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
import com.forumsite.test.web.page.CreateUserPage;
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
    public void editUserTest(){
        System.out.println("DEPURL" + deploymentUrl.toExternalForm());
        browser.get(deploymentUrl.toExternalForm() + "editThread.jsf?threadname=threadname1");
        assertEquals("Editing theadname1",browser.getTitle().trim());
        editPage.edit("threadname10", "category2");

        assertTrue(browser.getTitle().trim().equals("threadname10"));
        assertEquals("username123",threadPage.getThreadname());
        assertEquals("email@emal23.com",threadPage.getCategory());
    }
    
    @Test
    public void createUserInputMessagesTest(){
        browser.get(deploymentUrl.toExternalForm() + "editUser.jsf?username=username1");
        assertEquals("Editing username1",browser.getTitle().trim());
        editPage.edit("", "cat");
        assertFalse(editPage.getThreadNameError().getText().isEmpty());
        assertFalse(editPage.getCategoryError().getText().isEmpty());
    }
    
    @Test
    public void createUserDuplicateUsernameError(@InitialPage CreateUserPage editPage){
        fail("To be defined");
    }
    
    @Test
    public void createUserDuplicateEmailError(@InitialPage CreateUserPage editPage){
        fail("To be defined");
    }
    
    @Test
    public void createUserAccessDeniedError(@InitialPage CreateUserPage editPage){
        fail("To be defined");
    }    
    
}