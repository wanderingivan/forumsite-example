package com.forumsite.test.web.page;

import org.jboss.arquillian.graphene.page.Location;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.jboss.arquillian.graphene.Graphene.guardHttp;

@Location("user/newUser.jsf")
public class CreateUserPage {

    @FindBy(id="createUserForm:username")
    private WebElement usernameInput;
    
    @FindBy(id="createUserForm:password")
    private WebElement userPassword;
    
    @FindBy(id="createUserForm:email")
    private WebElement userEmail;
    
    @FindBy(id="createUserForm:description")
    private WebElement userDescription;
    
    @FindBy(id="createUserForm:create")
    private WebElement createButton;
    
    @FindBy(id="createUserForm:usernameError")
    private WebElement usernameError;
    

    @FindBy(id="createUserForm:passwordError")
    private WebElement passwordError;
    
    @FindBy(id="createUserForm:emailError")
    private WebElement emailError;
    
    @FindBy(id="createUserForm:descriptionError")
    private WebElement descriptionError;
    
    
    public void assertCreateUser(String username, String password,String email, String description){
        usernameInput.sendKeys(username);
        userPassword.sendKeys(password);
        userEmail.sendKeys(email);
        userDescription.sendKeys(description);
        guardHttp(createButton).click();
    }


    public WebElement getUsernameError() {
        return usernameError;
    }


    public WebElement getPasswordError() {
        return passwordError;
    }


    public WebElement getEmailError() {
        return emailError;
    }


    public WebElement getDescriptionError() {
        return descriptionError;
    }
    
    
    
    
}
