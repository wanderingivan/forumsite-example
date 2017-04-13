package com.forumsite.util;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.picketlink.idm.IdentityManager;
import org.picketlink.idm.PartitionManager;
import org.picketlink.idm.RelationshipManager;
import org.picketlink.idm.credential.Password;
import org.picketlink.idm.model.basic.BasicModel;
import org.picketlink.idm.model.basic.Realm;
import org.picketlink.idm.model.basic.Role;
import org.picketlink.idm.model.basic.User;

import static org.picketlink.idm.model.basic.BasicModel.grantRole;

@Singleton
@Startup
public class IdmInitializer {

    @Inject
    private PartitionManager partManager;
    
    @Inject
    Logger logger;
    
    
    @PostConstruct
    public void setUp(){
        Realm defaultRealm = this.partManager.getPartition(Realm.class, "default");
        if (defaultRealm == null){
            logger.info("Couldn't find default partition, creating default partition");
        
            defaultRealm = new Realm("default");
            this.partManager.add(defaultRealm);
        }
        IdentityManager idm = partManager.createIdentityManager(defaultRealm);
        Role admin = BasicModel.getRole(idm, "admin");
        Role user = BasicModel.getRole(idm, "user");
        if(admin == null ||  user == null){
            logger.info("Adding default user and admin roles");
            admin = new Role("admin");
            user = new Role("user");
            idm.add(admin);
            idm.add(user);
        }
        User adminAccount = BasicModel.getUser(idm, "admin");
        if(adminAccount == null){
            logger.info("Adding default admin account");
            adminAccount = new User("admin");
            idm.add(adminAccount);
            idm.updateCredential(adminAccount, new Password("password"));
            RelationshipManager relationshipManager = this.partManager.createRelationshipManager();
            grantRole(relationshipManager,adminAccount,admin);
        }

    }
    
}
