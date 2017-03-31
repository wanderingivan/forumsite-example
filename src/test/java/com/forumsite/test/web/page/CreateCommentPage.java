package com.forumsite.test.web.page;

import org.jboss.arquillian.graphene.Graphene;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateCommentPage {

    @FindBy(tagName="textarea")
    private WebElement commentMessage;
    
    @FindBy(id="commentForm:addComment")
    private WebElement addCommentButton;
    
    @FindBy(id="commentForm:messageError")
    private WebElement messageError;
    
    public void sendComment(String comment){
        commentMessage.click();
        commentMessage.sendKeys(comment);
        Graphene.guardHttp(addCommentButton).click();
    }


    public WebElement getMessageError() {
        return messageError;
    }
}
