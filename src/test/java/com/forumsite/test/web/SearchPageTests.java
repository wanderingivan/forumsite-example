package com.forumsite.test.web;

import static org.junit.Assert.*;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.graphene.page.Page;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.forumsite.test.util.Deployments;
import com.forumsite.test.web.page.SearchForm;
import com.forumsite.test.web.page.SearchResultsPage;

@RunWith(Arquillian.class)
public class SearchPageTests extends AbstractWebPageTests{

    
    @Deployment(testable = false)
    public static WebArchive createDeployment(){
        return Deployments.projectWar();
    }
    
    @Page
    SearchResultsPage sPage;
    
    @Page
    SearchForm sForm;
    
    @Test
    public void testSearchExistingThreads(){
        browser.get(deploymentUrl.toExternalForm() + "main.jsf");
        sForm.searchThreads("thread");
        assertEquals(5, sPage.getTopics().size());
        assertEquals("Topics matching thread", sPage.getMessage());
    }
    
    @Test
    public void testSearchMissingThreads(){
        browser.get(deploymentUrl.toExternalForm() + "main.jsf");
        sForm.searchThreads("missing");
        assertEquals(0, sPage.getTopics().size());
        assertEquals("No results for query missing", sPage.getMessage());
    }
    
    @Test
    public void testSearchByCategory(){
        browser.get(deploymentUrl.toExternalForm() + "category.jsf?category=category2");
        sForm.searchThreadsCategory("thread");
        assertEquals(2, sPage.getTopics().size());
        assertEquals("Topics matching thread", sPage.getMessage());
    }
}
