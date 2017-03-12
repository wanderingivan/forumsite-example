package com.forumsite.test.persistence;

import static org.junit.Assert.*;

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
        repo.createComment(c,"threadname1","username2");
        ForumThread t = fRepo.findThread(11l);
        Comment testComment = t.getComments().get(t.getComments().size()-1);
        assertEquals(c.getMessage(),testComment.getMessage());
        User author = uRepo.getUserByName("username2");
        assertEquals(author,testComment.getAuthor());
        repo.delete(c.getId());
        fRepo.delete(t.getId());
    }
    
    @Test
    public void testRetrieveComment(){
        Comment c = repo.retrieveComment(11l);
        assertNotNull("Recieved null instance", c);
        assertEquals("A message", c.getMessage());
        assertEquals("username2", c.getAuthor().getUsername());
        assertEquals(new Long(11),c.getThread().getId());
    }
    
    @Test
    public void testUpdateComment(){
        repo.updateComment(11l,"Edited message");
        Comment testComment = repo.retrieveComment(11l);
        assertEquals("Edited message",testComment.getMessage());
    }
    
    @Test
    public void deleteComment(){
        repo.delete(11l);
        assertNull(repo.retrieveComment(11l));
    }
    
}
