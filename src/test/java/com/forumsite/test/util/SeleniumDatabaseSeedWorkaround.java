package com.forumsite.test.util;

import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.log4j.Logger;
import org.picketlink.idm.IdentityManager;
import org.picketlink.idm.PermissionManager;
import org.picketlink.idm.RelationshipManager;
import org.picketlink.idm.credential.Password;
import org.picketlink.idm.model.basic.BasicModel;

import com.forumsite.model.Comment;
import com.forumsite.model.ForumThread;
import com.forumsite.model.User;


/**
 * An ugly workaround for seeding the database
 * for arquillian backed selenium tests  as
 * their persistence extension can't work in client mode
 * and the mixed mode solutions don't seem to work in
 * the current version as far as {@code WebDriver} is concerned 
 */

@Stateless
@SuppressWarnings("unchecked")
public class SeleniumDatabaseSeedWorkaround {

    @Inject
    private EntityManager em;
    
    @Inject
    private Logger logger;
    
    @Inject
    private IdentityManager idm;
    
    @Inject
    private PermissionManager permissionManager;
    
    @Inject
    private RelationshipManager relManager;
    
    public void initDatabase(@Observes @Initialized(ApplicationScoped.class) final Object event){
      if(logger.isInfoEnabled()){
          logger.info("Workaround seeding database instead of DBUnit");
      }
      initUsers(); 
      initThreads();
      if(logger.isDebugEnabled()){
          logger.info("ThreadList: \n" +em.createQuery("SELECT ft FROM ForumThread ft").getResultList());
          logger.info("UserList: \n" +em.createQuery("SELECT u FROM Users u").getResultList());
      }
    }
    
    
    private void initUsers(){
       for(int i = 2; i < 4; i++){
           User u = new User("username"+i,"password","email@email"+i+".com"); 
           em.persist(u);
       }
       addUserAcl();
    }
    
    private void addUserAcl(){
        for(User u : ((List<User>) em.createQuery("SELECT u FROM Users u").getResultList())){
            org.picketlink.idm.model.basic.User user = new org.picketlink.idm.model.basic.User(u.getUsername());
            user.setEmail(u.getEmail());
            user.setId(u.getUsername());
            idm.add(user);
            idm.updateCredential(user, new Password(u.getPassword()));
            permissionManager.grantPermission(user, u, "update");
            BasicModel.grantRole(relManager, user, BasicModel.getRole(idm, "user"));
        }
    }
    
    private void initThreads(){
        User u = em.createNamedQuery("User.findByName", User.class)
                   .setParameter("username", "username2")
                   .getSingleResult();
        
        org.picketlink.idm.model.basic.User firstUser = BasicModel.getUser(idm, "username2");
        org.picketlink.idm.model.basic.User secondUser  = BasicModel.getUser(idm, "username3");
        
        for(int i = 1; i < 4; i++){
            ForumThread t = new ForumThread("threadname"+i, "category1", u);
            em.persist(t);
            permissionManager.grantPermission(firstUser, t, "update");
            for(int k = 0;k < 15;k++){
                Comment c = new Comment(t,u,"A comment");
                em.persist(c);
            }
        }
        
        for(int i = 5; i < 7; i++){
            ForumThread t = new ForumThread("threadname"+i, "category2", u);
            em.persist(t);
            permissionManager.grantPermission(secondUser, t, "update");
            for(int k = 0;k < 5;k++){
                Comment c = new Comment(t,u,"A comment");
                em.persist(c);
            }
        }
        
        for(int i = 1;i < 7;i++){

        }
    }
}
