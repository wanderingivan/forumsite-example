package com.forumsite.test.web.page;

import org.jboss.arquillian.graphene.Graphene;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditCommentPage {
    
    @FindBy(tagName="textarea")
    private WebElement commentMessage;
    
    @FindBy(id="commentEditForm:editComment")
    private WebElement editCommentButton;
    
    @FindBy(id="commentEditForm:messageError")
    private WebElement messageError;
    
    public void editComment(String comment){
        commentMessage.sendKeys(comment);
        Graphene.guardHttp(editCommentButton).click();
    }

    public WebElement getMessageError() {
        return messageError;
    }
}
