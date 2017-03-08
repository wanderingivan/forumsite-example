package com.forumsite.test.web.page;

import org.jboss.arquillian.graphene.Graphene;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditThreadPage {

    @FindBy(id="editThreadForm:threadname")
    private WebElement threadName;
    
    @FindBy(id="editThreadForm:category")
    private WebElement category;
    
    @FindBy(id="editThreadForm:edit")
    private WebElement edit;
    
    @FindBy(id="editThreadForm:nameError")
    private WebElement threadNameError;
    
    @FindBy(id="editThreadForm:categoryError")
    private WebElement categoryError;
    
    public void edit(String threadName, String category){
        this.threadName.clear();
        this.threadName.sendKeys(threadName);
        this.category.clear();
        this.category.sendKeys(category);
        Graphene.guardHttp(edit).click();       
    }

    public WebElement getThreadNameError() {
        return threadNameError;
    }

    public WebElement getCategoryError() {
        return categoryError;
    }
    
}
