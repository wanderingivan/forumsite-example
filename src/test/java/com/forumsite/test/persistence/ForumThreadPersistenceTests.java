package com.forumsite.test.persistence;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.forumsite.data.ForumThreadRepository;
import com.forumsite.data.UserRepository;
import com.forumsite.model.ForumThread;
import com.forumsite.test.util.Deployments;


@UsingDataSet("persistence-test-dataset.xml")
@RunWith(Arquillian.class)
public class ForumThreadPersistenceTests {

    @Deployment
    public static WebArchive createDeployment(){
        return Deployments.persistenceWar();        
    }
    
    @Inject
    ForumThreadRepository repo;
    
    @Inject
    UserRepository uRepo;
    
    @Test
    public void testGetThreadByName(){
        ForumThread t  = repo.getThreadByName("threadname1");
        assertNotNull(t);
    }
    
    @Test
    public void testCreateThread(){
        ForumThread f = new ForumThread("newthread", "category");
        repo.save(f,"A message","username1");
        ForumThread t = repo.getThreadByName("newthread");
        assertNotNull(t);
        assertTrue(t.equals(f));
        assertNotNull(t.getAuthor());
        assertEquals("username1",t.getAuthor().getUsername());
        assertEquals("A message",t.getComments().get(0).getMessage());
        repo.delete(t.getId());
    }
    
    @Test
    public void testUpdateThread(){
        ForumThread f = repo.findThread(11L);
        f.setName("updatename");
        f.setCategory("updatedCat");
        repo.update(f);
        ForumThread t = repo.findThread(11L);
        assertNotNull(t);
        assertTrue(t.equals(f));
    }
    
    @Test
    public void deleteThread(){
        repo.delete(11L);
        assertNull(repo.findThread(11L));
    }
    
    @Test
    public void testListThread(){
        assertEquals(5, repo.list().size());
    }
    
    @Test
    public void testSearchThreads(){
        assertEquals(5, repo.searchThreads("thread").size());
    }

    @Test
    public void testSearchThreadsByCategory(){
        assertEquals(1, repo.searchThreads("thread","category1").size());
    }
    
    @Test
    public void testGetCategory(){
        assertEquals(4, repo.loadCategory("category").size());
    }
  
}
