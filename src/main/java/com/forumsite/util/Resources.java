package com.forumsite.util;

import java.io.File;
import java.io.IOException;
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
    
    private static final String DEFAULT_IMAGE_FOLDER = "/src/images";
    
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
    public ImageUtil imageUtil() throws IOException {
        String imageFolder = System.getProperty("user.dir").concat(DEFAULT_IMAGE_FOLDER);
        if(!new File(imageFolder).exists()){
            throw new IOException("Misconfiguration: Cannot access imagefolder - images won't load ");
        }
        ImageUtil util = new ImageUtil(imageFolder,true,2000000);
        return util;
    }
}
