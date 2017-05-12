package com.forumsite.test.validation;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.forumsite.model.ForumThread;
import com.forumsite.test.util.Deployments;


@RunWith(Arquillian.class)
public class ForumThreadValidationTests extends AbstractValidationTest {

    @Deployment
    public static JavaArchive createDeployment(){
        return Deployments.validationsJar();
    }
    
    @Inject
    Validator validator;
    
    @Test
    public void testRegisterEmptyThread(){
        ForumThread thread = new ForumThread();
        Set<ConstraintViolation<ForumThread>> violations = validator.validate(thread);
        assertEquals(2, violations.size());
    }
    
    @Test
    public void testRegisterValidThread(){

        ForumThread thread = new ForumThread("threadname","Role Play Game");
        Set<ConstraintViolation<ForumThread>> violations = validator.validate(thread);
        assertEquals(0, violations.size());
        
        thread = new ForumThread("threadname","First Person Shooter");
        violations = validator.validate(thread);
        assertEquals(0, violations.size());
        
        thread = new ForumThread("threadname","Real Time Strategy");
        violations = validator.validate(thread);
        assertEquals(0, violations.size());
        
        thread = new ForumThread("threadname","Sports");
        violations = validator.validate(thread);
        assertEquals(0, violations.size());
        
        thread = new ForumThread("threadname","Offtopic");
        violations = validator.validate(thread);
        assertEquals(0, violations.size());
    }
    
    @Test
    public void testRegisterInvalidThreadname(){
        ForumThread thread = new ForumThread(testStringOfSize(60),"Sports");
        Set<ConstraintViolation<ForumThread>> violations = validator.validate(thread);
        assertEquals(1, violations.size());       
    }
    
    @Test //XXX This test will be incomplete until category names are picked
    public void testRegisterInvalidCategory(){
        ForumThread thread = new ForumThread("threadname","");
        Set<ConstraintViolation<ForumThread>> violations = validator.validate(thread);
        assertEquals(1, violations.size());       

        thread = new ForumThread("threadname","Something else");
        violations = validator.validate(thread);
        assertEquals(1, violations.size());       
    }
    
}
