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
public class SearchPageTests extends AbstractWebPageTest{

    
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
        loadPage("main.jsf");
        sForm.searchThreads("Red");
        assertEquals(1, sPage.getTopics().size());
        assertEquals("Topics matching Red", sPage.getMessage());
    }
    
    @Test
    public void testSearchMissingThreads(){
        loadPage("main.jsf");
        sForm.searchThreads("missing");
        assertEquals(0, sPage.getTopics().size());
        assertEquals("No results for query missing", sPage.getMessage());
    }
    
}
