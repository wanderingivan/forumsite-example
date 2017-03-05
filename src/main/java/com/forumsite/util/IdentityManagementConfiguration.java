package com.forumsite.util;

import javax.enterprise.event.Observes;

import org.picketlink.config.SecurityConfigurationBuilder;
import org.picketlink.event.SecurityConfigurationEvent;

public class IdentityManagementConfiguration {

    
    public void configureIdentityManagement(@Observes SecurityConfigurationEvent event){
        SecurityConfigurationBuilder builder = event.getBuilder();
        
        builder
            .idmConfig()
                .named("default")
                    .stores()
                        .jpa()
                            .supportAllFeatures();
    } 
}
