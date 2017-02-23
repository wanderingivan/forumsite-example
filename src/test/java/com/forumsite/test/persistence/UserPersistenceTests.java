package com.forumsite.test.persistence;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.forumsite.data.UserRepository;
import com.forumsite.model.User;
import com.forumsite.test.util.Deployments;

@UsingDataSet("persistence-test-dataset.xml")
@RunWith(Arquillian.class)
public class UserPersistenceTests {

    @Deployment
    public static WebArchive createDeployment(){
        return Deployments.persistenceWar();        
    }
    
    @Inject
    UserRepository repo;
    
    @Test
    public void testCreateUser(){
        User u = new User("username4","password","email@test.com");
        repo.save(u);
        User t = repo.getUserByName("username4");
        assertNotNull(t);
        assertEquals(u,t);
    }
    
    @Test
    public void testReadUser(){
        User u = repo.getUserByName("username1");
        assertNotNull(u);
        assertEquals("username1",u.getUsername());
    }
    
    @Test
    public void testUpdateUser(){
        User u = repo.findUser(11L);   
        u.setUsername("newusername");
        u.setEmail("newemail@email.com");
        repo.update(u);
        User t = repo.findUser(11L);
        assertNotNull(t);
        assertEquals(u,t);
        assertEquals("newemail@email.com",t.getEmail());
    }
    
    @Test
    public void testDeleteUser(){
        repo.delete(11L);
        assertNull(repo.findUser(11L));
    }
    
    @Test
    public void testGetLatestUsers(){
        assertEquals(3,repo.listUsers().size());
    }

    @Test
    public void testFindUsers(){
        assertEquals(3,repo.searchUsers("username").size());
    }
    
}
