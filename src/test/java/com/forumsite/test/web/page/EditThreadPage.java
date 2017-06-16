package com.forumsite.test.web.page;

import org.jboss.arquillian.graphene.Graphene;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class EditThreadPage {

    @FindBy(id="editThreadForm:threadname")
    private WebElement threadName;
    
    @FindBy(id="editThreadForm:category")
    private Select category;
    
    @FindBy(id="editThreadForm:edit")
    private WebElement edit;
    
    @FindBy(id="editThreadForm:nameError")
    private WebElement threadNameError;
    
    @FindBy(id="editThreadForm:categoryError")
    private WebElement categoryError;
    
    public void edit(String threadName, String category){
        this.threadName.clear();
        this.threadName.sendKeys(threadName);
        this.category.selectByValue(category);
        Graphene.guardHttp(edit).click();       
    }

    public String getThreadNameError() {
        return threadNameError.getText().trim();
    }

    public String getCategoryError() {
        return categoryError.getText().trim();
    }
    
}
