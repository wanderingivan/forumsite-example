package com.forumsite.util;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

import org.picketlink.config.SecurityConfigurationBuilder;
import org.picketlink.event.SecurityConfigurationEvent;
@ApplicationScoped
public class IdentityManagementConfiguration {

    
    public void configureIdentityManagement(@Observes SecurityConfigurationEvent event){
        SecurityConfigurationBuilder builder = event.getBuilder();
        
        builder
            .idmConfig()
                .named("DEFAULT")
                    .stores()
                        .jpa()
                            .supportAllFeatures();
    } 
}
