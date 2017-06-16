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
        repo.add(u);
        Optional<User> t = repo.getByName("username4");
        assertTrue("No user was retrieved",t.isPresent());
        assertEquals(u,t.get());
    }
    
    @Test
    public void testReadUser(){
        Optional<User> u = repo.getByName("username1");
        assertTrue("No user was retrieved",u.isPresent());
        assertEquals("username1",u.get()
                                  .getUsername());
    }
    
    @Test
    public void testUpdateUser(){
        User u = repo.get(11L).get();   
        u.setUsername("newusername");
        u.setEmail("newemail@email.com");
        repo.update(u);
        Optional<User> t = repo.get(11L);
        assertTrue(t.isPresent());
        assertEquals(u,t.get());
        assertEquals("newemail@email.com",t.get()
                                           .getEmail());
    }
    
    @Test
    public void testDeleteUser(){
        repo.delete(11L);
        assertFalse(repo.get(11L).isPresent());
    }
    
    @Test
    public void testGetLatestUsers(){
        assertEquals(3,repo.list().size());
    }

    @Test
    public void testFindUsers(){
        assertEquals(3,repo.search("username").size());
    }

    @Test
    public void testCheckUsernameExistingUser(){
        assertFalse(repo.checkUsername("username1"));
    }
    
    @Test
    public void testCheckUsernameAvailable(){
        assertTrue(repo.checkUsername("username100"));
    }
    
    @Test
    public void testCheckEmailExisting(){
        assertFalse(repo.checkEmail("email@email.com1"));
    }
    
    @Test
    public void testCheckEmailAvailable(){
        assertTrue(repo.checkEmail("email@email.com100"));
    }    
}
