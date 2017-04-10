package com.forumsite.test.persistence;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.forumsite.data.CommentRepository;
import com.forumsite.data.ForumThreadRepository;
import com.forumsite.data.UserRepository;
import com.forumsite.model.Comment;
import com.forumsite.model.ForumThread;
import com.forumsite.model.User;
import com.forumsite.test.util.Deployments;

@UsingDataSet("persistence-test-dataset.xml")
@RunWith(Arquillian.class)
public class CommentPersistenceTests {

    @Deployment
    public static WebArchive createDeployment(){
        return Deployments.persistenceWar();        
    }
    
    @Inject
    CommentRepository repo;
    
    @Inject
    UserRepository uRepo;
    
    @Inject
    ForumThreadRepository fRepo;
    
    @Test
    public void testCreateComment(){
        Comment c = new Comment("A message");
        repo.add(c,"threadname1","username2");
        ForumThread t = fRepo.get(11l).get();
        Comment testComment = t.getComments().get(t.getComments().size()-1);
        assertEquals(c.getMessage(),testComment.getMessage());
        User author = uRepo.getByName("username2").get();
        assertEquals(author,testComment.getAuthor());
        repo.delete(c.getId());
        fRepo.delete(t.getId());
    }
    
    @Test
    public void testRetrieveComment(){
        Optional<Comment> o = repo.get(11l);
        assertTrue("No comment was retrieved",o.isPresent());
        Comment c = o.get();
        assertEquals("A message", c.getMessage());
        assertEquals("username2", c.getAuthor().getUsername());
        assertEquals((long)11,c.getThread().getId());
    }
    
    @Test
    public void testUpdateComment(){
        repo.update(11l,"Edited message");
        Comment testComment = repo.get(11l).get();
        assertEquals("Edited message",testComment.getMessage());
    }
    
    @Test
    public void deleteComment(){
        repo.delete(11l);
        assertFalse(repo.get(11l).isPresent());
    }
    
    @Test
    public void testGetForThread(){
        List<Comment> comments = repo.getForThread("threadname1");
        assertNotNull(comments);
        assertEquals(1,comments.size());
        Comment c = comments.get(0);
        assertEquals("A message", c.getMessage());
        assertEquals("username2", c.getAuthor().getUsername());
        assertEquals((long)11,c.getThread().getId());
    }
    
}
