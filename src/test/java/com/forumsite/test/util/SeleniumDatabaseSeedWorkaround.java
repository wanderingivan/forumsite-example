package com.forumsite.test.util;

import java.util.stream.IntStream;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import com.forumsite.model.ForumThread;
import com.forumsite.model.User;


/**
 * An ugly workaround for seeding the database
 * for arquillian backed selenium tests  as
 * their persistence extension can't work in client mode
 * and the mixed mode solutions don't seem to work in
 * the current version as far as {@code WebDriver} is concerned 
 */

@Startup
@Singleton
public class SeleniumDatabaseSeedWorkaround {

    @Inject
    private EntityManager em;
    
    @Inject
    private Logger logger;
    
    @PostConstruct
    public void initDatabase(){
      if(logger.isInfoEnabled()){
          logger.info("Workaround seeding database instead of DBUnit");
      }
      initUsers(); 
      initThreads();
    }
    
    private void initUsers(){
       IntStream.of(1,4)
                .mapToObj(i -> {
                    return new User("username"+i,"password","email@email"+i+".com");
                })
                .forEach(u -> em.persist(u));
    }
    
    private void initThreads(){
        User u = em.createNamedQuery("User.findByName", User.class)
                   .setParameter("username", "username1")
                   .getSingleResult();
        IntStream.of(1,4)
                 .mapToObj(i -> {
                     return new ForumThread("threadname"+i, "category", u);
                 })
                 .forEach(t -> em.persist(u));
    }
}
