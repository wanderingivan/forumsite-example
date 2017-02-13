package com.forumsite.test.validation;

import javax.validation.Validator;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import com.forumsite.model.User;
import com.forumsite.test.util.Deployments;

import java.util.Set;

@RunWith(Arquillian.class)
public class UserValidationTests extends AbstractValidationTest {

    @Deployment
    public static JavaArchive createDeployment(){
        return Deployments.validationsJar();
    }
    
    @Inject
    Validator validator;
    
    @Test
    public void testRegisterEmptyUser(){
        User user = new User();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(3, violations.size());
    }
    
    @Test
    public void testRegisterValidUser(){
        User user = new User("username","password","email@test.com");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(0, violations.size());
    }
    
    @Test
    public void testRegisterInvalidUsername(){
        User user = new User("user","password","email@test.com");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());       
    }
    
    @Test
    public void testRegisterInvalidEmail(){
        User user = new User("username","password","email@tescom");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());       
    }
    
    @Test
    public void testRegisterInvalidPassword(){
        User user = new User("username","pass","email@test.com");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());       
    }
    
    @Test
    public void testRegisterInvalidUsernameSize(){
        User user = new User(testStringOfSize(60),"password","email@test.com");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());       
    }
    
    @Test
    public void testRegisterInvalidPasswordSize(){
        User user = new User("username",testStringOfSize(60),"email@test.com");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());       
    }
    
    @Test
    public void testRegisterInvalidDescriptionSize(){
        User user = new User("username","password","email@test.com");
        user.setDescription(testStringOfSize(300));
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());       
    }
    
}
