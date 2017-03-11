package com.forumsite.test.web.page;

import static org.junit.Assert.*;

import org.jboss.arquillian.graphene.GrapheneElement;
import org.openqa.selenium.support.FindBy;

public class ErrorPage {
    
    @FindBy(id="error-message")
    GrapheneElement errorMessage;

    public void assertOnAccessDeniedPage() {
        assertTrue("The error message is missing.",errorMessage.isPresent());
        assertEquals("Sorry you must be logged in to proceed", errorMessage.getText().trim());
    }

}
