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

import com.forumsite.model.Comment;
import com.forumsite.test.util.Deployments;

@RunWith(Arquillian.class)
public class CommentValidationTests extends AbstractValidationTest {

    @Deployment
    public static JavaArchive createDeployment(){
        return Deployments.validationsJar();
    }
    
    @Inject
    Validator validator;
    
    @Test
    public void testRegisterEmptyComment(){
        Comment comment = new Comment();
        Set<ConstraintViolation<Comment>> violations = validator.validate(comment);
        assertEquals(1, violations.size());
    }
    
    @Test
    public void testRegisterValidComment(){
        Comment comment = new Comment("message");
        Set<ConstraintViolation<Comment>> violations = validator.validate(comment);
        assertEquals(0, violations.size());
    }
    
    @Test
    public void testRegisterInvalidCommentMessage(){
        Comment comment = new Comment("");
        Set<ConstraintViolation<Comment>> violations = validator.validate(comment);
        assertEquals(1, violations.size());       
    }
    
    
    @Test
    public void testRegisterInvalidCommentSize(){
        Comment comment = new Comment(testStringOfSize(1300));
        Set<ConstraintViolation<Comment>> violations = validator.validate(comment);
        assertEquals(1, violations.size());       
    }
    
}
