package com.forumsite.test.web.page;


import org.jboss.arquillian.graphene.page.Location;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.junit.Assert.assertEquals;

@Location("scheck.jsf")
public class SanityCheckPage {
    

    @FindBy(id="scheck")
    private WebElement header1;
    
    @FindBy(id="facesScheck")
    private WebElement facesMessage;
    
    public void assertSanityCheck(){
        assertEquals("WebApp Config Passes Basic Sanity Check",header1.getText().trim());
        assertEquals("It works",facesMessage.getText().trim());
    }
    
    
}
