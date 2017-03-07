package com.forumsite.test.web.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShowThreadPage {
    
    @FindBy(id="threadname")
    private WebElement threadname;
    
    @FindBy(id="category")
    private WebElement category;

    public WebElement getThreadname() {
        return threadname;
    }

    public WebElement getCategory() {
        return category;
    }
}
