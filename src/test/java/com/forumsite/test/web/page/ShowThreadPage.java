package com.forumsite.test.web.page;

import org.jboss.arquillian.graphene.GrapheneElement;
import org.jboss.arquillian.graphene.findby.FindByJQuery;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShowThreadPage {
    
    @FindBy(id="threadname")
    private WebElement threadname;
    
    @FindBy(id="category")
    private WebElement category;
    
    @FindByJQuery(".threadComment:eq(0)")
    private GrapheneElement firstComment;

    public String getThreadname() {
        return threadname.getText().trim();
    }

    public String getCategory() {
        return category.getText().trim();
    }
    
    public String getFirstComment() {
        return firstComment.getText().trim();
    }
    
    public boolean commentExists(){
        return firstComment.isPresent();
    }
}
