package com.forumsite.util;

import javax.enterprise.event.Observes;

import org.picketlink.config.SecurityConfigurationBuilder;
import org.picketlink.event.SecurityConfigurationEvent;

public class HttpSecurityConfiguration {

    public void configureHttpSecurity(@Observes SecurityConfigurationEvent event){
        SecurityConfigurationBuilder builder = event.getBuilder();
        builder.http()
               .forGroup("Web Pages")
                   .authenticateWith()
                       .form()
                           .loginPage("/login.jsf")
                           .errorPage("/403.jsf")
                           
               .forPath("/thread/newThread.jsf","Web Pages")

               .forPath("/thread/editThread.jsf","Web Pages")

               .forPath("/thread/replyThread.jsf","Web Pages")

               .forPath("/comment/editComment.jsf","Web Pages")

               .forPath("/user/editUser.jsf","Web Pages");
                     
    }
}
