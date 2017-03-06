package com.forumsite.test.web.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;



public class ShowUserPage {

    @FindBy(id="username")
    WebElement username;
    
    @FindBy(id="email")
    WebElement email;
    
    @FindBy(id="description")
    WebElement description;
    
    public String getUsername() {
        return username.getText().trim();
    }

    public String getEmail() {
        return email.getText().trim();
    }

    public String getDescription() {
        return description.getText().trim();
    }

}
