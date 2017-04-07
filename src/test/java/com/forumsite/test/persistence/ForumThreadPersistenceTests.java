package com.forumsite.test.persistence;

import static org.junit.Assert.*;

import java.util.Optional;

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
    public void testGetThreadByName() throws Exception{
        Optional<ForumThread> o = repo.getByName("threadname1");
        assertTrue(o.isPresent());
        ForumThread t = o.get();
        assertNotNull(t.getLastComment());
        assertNotNull(t.getLastComment().getAuthor());
        assertEquals("username2",t.getLastComment().getAuthor().getUsername());
        assertEquals("threadname1", t.getName());
        assertEquals("category", t.getCategory());
        assertNotNull(t.getAuthor());
        assertEquals("username1",t.getAuthor().getUsername());
        assertNotNull(t.getComments());
        assertEquals(1,t.getComments().size());
        assertEquals("A message",t.getComments().get(0).getMessage());
    }
    
    @Test
    public void testCreateThread(){
        ForumThread f = new ForumThread("newthread", "category");
        repo.add(f,"A message","username1");
        Optional<ForumThread> o = repo.getByName("newthread");
        assertTrue("No thread was returned",o.isPresent());
        ForumThread t = o.get();
        assertTrue("Saved thread is no equal to provided",t.equals(f));
        assertNotNull("Thread author is null",t.getAuthor());
        assertEquals("username1",t.getAuthor().getUsername());
        assertEquals("A message",t.getComments().get(0).getMessage());
        repo.delete(t.getId());
    }
    
    @Test
    public void testUpdateThread(){
        ForumThread f = repo.get(11L).get();
        f.setName("updatename");
        f.setCategory("updatedCat");
        repo.update(f);
        Optional<ForumThread> o = repo.get(11L);
        assertTrue(o.isPresent());
        ForumThread t = o.get();
        assertTrue(t.equals(f));
    }
    
    @Test
    public void deleteThread(){
        repo.delete(11L);
        assertFalse(repo.get(11L).isPresent());
    }
    
    @Test
    public void testListThread(){
        assertEquals(5, repo.list().size());
    }
    
    @Test
    public void testSearchThreads(){
        assertEquals(5, repo.search("thread").size());
    }

    @Test
    public void testSearchThreadsByCategory(){
        assertEquals(1, repo.search("thread","category1").size());
    }
    
    @Test
    public void testGetCategory(){
        assertEquals(4, repo.getCategory("category").size());
    }
  
}
