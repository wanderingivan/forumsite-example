package com.forumsite.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;


public class Resources {
    
    @Produces
    @PersistenceContext
    private EntityManager em;

    @Produces
    public Logger getLogger(InjectionPoint ip) {
        String category = ip.getMember().getDeclaringClass().getName();
        return Logger.getLogger(category);
    }

    @Produces
    @RequestScoped
    public FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }
    
    @Named
    @Produces
    @ApplicationScoped
    public List<String> categories(){
        return Collections.unmodifiableList(Arrays.asList("Real Time Strategy",
                                                          "First Person Shooter",
                                                          "Role Play Game",
                                                          "Sports",
                                                          "Offtopic"));
    }
    
    @Produces
    @ApplicationScoped
    @ConfiguredImageUtil
    public ImageUtil imageUtil(){
        return new ImageUtil("/tmp/siteimage",true,2000000);
    }
}
