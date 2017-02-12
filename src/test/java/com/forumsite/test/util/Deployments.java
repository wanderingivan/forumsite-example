package com.forumsite.test.util;

import java.io.File;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;


@SuppressWarnings("rawtypes")
public class Deployments {
    private static final String WEBAPP_SRC = "src/main/webapp";
    
    public static WebArchive basicWar(Class [] classes){
        return ShrinkWrap.create(WebArchive.class)
                .addClasses(classes)
                .addAsResource("persistence.xml","META-INF/persistence.xml")
                .addAsWebInfResource("test-ds.xml")
                .addAsWebResource(new File(WEBAPP_SRC, "index.xhtml"))
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsWebInfResource(
                    new StringAsset("<faces-config version=\"2.0\"/>"),
                    "faces-config.xml");
    }
}
