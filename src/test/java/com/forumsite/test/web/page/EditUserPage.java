package com.forumsite.test.web.page;

import org.jboss.arquillian.graphene.page.Location;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.jboss.arquillian.graphene.Graphene.guardHttp;

@Location("editUser.jsf")
public class EditUserPage {

    @FindBy(id="editUserForm:username")
    private WebElement usernameInput;
    
    @FindBy(id="editUserForm:email")
    private WebElement userEmail;
    
    @FindBy(id="editUserForm:description")
    private WebElement userDescription;
    
    @FindBy(id="editUserForm:edit")
    private WebElement editButton;
    
    @FindBy(id="editUserForm:usernameError")
    private WebElement usernameError;
    
    @FindBy(id="editUserForm:emailError")
    private WebElement emailError;
    
    @FindBy(id="editUserForm:descriptionError")
    private WebElement descriptionError;
    
    
    public void editUser(String username, String email, String description){
        usernameInput.clear();
        usernameInput.sendKeys(username);
        userEmail.clear();
        userEmail.sendKeys(email);
        userDescription.clear();
        userDescription.sendKeys(description);
        guardHttp(editButton).click();
    }


    public WebElement getUsernameError() {
        return usernameError;
    }


    public WebElement getEmailError() {
        return emailError;
    }


    public WebElement getDescriptionError() {
        return descriptionError;
    }
    
}
