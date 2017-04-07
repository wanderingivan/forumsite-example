package com.forumsite.util;

import javax.enterprise.event.Observes;

import org.picketlink.config.SecurityConfigurationBuilder;
import org.picketlink.event.SecurityConfigurationEvent;

public class HttpSecurityConfiguration {

    public void configureHttpSecurity(@Observes SecurityConfigurationEvent event){
        SecurityConfigurationBuilder builder = event.getBuilder();
        builder.http()
                .forPath("/newThread.jsf")
                    .authenticateWith()
                        .form()
                            .loginPage("/login.jsf")
                            .errorPage("/error.jsf")
                .forPath("/editThread.jsf")
                    .authenticateWith()
                        .form()
                            .loginPage("/login.jsf")
                            .errorPage("/error.jsf")
                .forPath("/replyThread.jsf")
                    .authenticateWith()
                        .form()
                            .loginPage("/login.jsf")
                            .errorPage("/error.jsf")
                .forPath("/editComment.jsf")
                    .authenticateWith()
                        .form()
                            .loginPage("/login.jsf")
                            .errorPage("/error.jsf")
                .forPath("/editUser.jsf")
                    .authenticateWith()
                        .form()
                            .loginPage("/login.jsf")
                            .errorPage("/error.jsf")                              
                .forPath("/test/*")
                    .authorizeWith()
                        .group("admin").authenticateWith()
                        .form()
                            .loginPage("/login.jsf")                        
                        .errorPage("/error.jsf");                              
    }
}
