package com.forumsite.test.util;

import java.io.File;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.archive.importer.MavenImporter;

import com.forumsite.data.ForumThreadRepository;
import com.forumsite.data.impl.ForumThreadRepositoryImpl;
import com.forumsite.data.UserRepository;
import com.forumsite.data.impl.UserRepositoryImpl;
import com.forumsite.model.Comment;
import com.forumsite.model.User;
import com.forumsite.model.ForumThread;
import com.forumsite.test.validation.AbstractValidationTest;
import com.forumsite.util.Resources;


@SuppressWarnings("rawtypes")
public class Deployments {
    private static final String WEBAPP_SRC = "src/main/webapp";
    
    public static WebArchive projectWar(){
        return ShrinkWrap.create(MavenImporter.class,"testWar.war")
                         .loadPomFromFile("pom.xml")
                         .importBuildOutput()
                         .as(WebArchive.class);
    }
    
    public static WebArchive basicSeleniumWar(Class [] classes){
        return ShrinkWrap.create(WebArchive.class)
                         .addClasses(classes)
                         .addAsResource("persistence.xml","META-INF/persistence.xml")
                         .addAsWebInfResource("test-ds.xml")
                         .addAsWebResource(new File(WEBAPP_SRC, "main.xhtml"))
                         .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                         .addAsWebInfResource(
                               new StringAsset("<faces-config version=\"2.0\"/>"),
                               "faces-config.xml");
    }
    
    public static WebArchive basicWar(Class [] classes){
        return ShrinkWrap.create(WebArchive.class)
                         .addClasses(classes)
                         .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }
    
    public static JavaArchive validationsJar(){
        return basicJar(new Class[]{User.class,ForumThread.class,Comment.class,AbstractValidationTest.class});
    }
    
    
    public static JavaArchive basicJar(Class [] classes){
        return ShrinkWrap.create(JavaArchive.class)
                         .addClasses(classes)
                         .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
    
    public static WebArchive persistenceWar(){
        return basicWar(new Class[]{User.class,ForumThread.class,Comment.class,Resources.class,UserRepository.class,UserRepositoryImpl.class,ForumThreadRepository.class,ForumThreadRepositoryImpl.class})//XXX Do not commit
                       .addAsResource("persistence.xml","META-INF/persistence.xml")
                       .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                       .addAsWebInfResource("test-ds.xml")
                       .addAsWebInfResource(
                           new StringAsset("<faces-config version=\"2.0\"/>"),
                        "faces-config.xml");
    }
}
