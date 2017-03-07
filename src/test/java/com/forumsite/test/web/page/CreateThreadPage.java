package com.forumsite.test.web.page;

import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.page.Location;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Location("newThread.jsf")
public class CreateThreadPage {

    @FindBy(id="createThreadForm:threadname")
    private WebElement threadName;
    
    @FindBy(id="createThreadForm:category")
    private WebElement category;
    
    @FindBy(id="createThreadForm:initialMessage")
    private WebElement initialMessage;
    
    @FindBy(id="createThreadForm:create")
    private WebElement create;
    
    @FindBy(id="createThreadForm:nameError")
    private WebElement threadNameError;
    
    @FindBy(id="createThreadForm:categoryError")
    private WebElement categoryError;
    
    @FindBy(id="createThreadForm:messageError")
    private WebElement messageError;
    
    public void createThread(String threadName, String category, String initialMessage) {
        this.threadName.sendKeys(threadName);
        this.category.sendKeys(category);
        this.initialMessage.sendKeys(initialMessage);
        Graphene.guardHttp(create).click();
    }

    public WebElement getThreadNameError() {
        return threadNameError;
    }

    public WebElement getCategoryError() {
        return categoryError;
    }

    public WebElement getMessageError() {
        return messageError;
    }
}
