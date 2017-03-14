package com.forumsite.test.web.page;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchResultsPage {

    @FindBy(className="topic")
    private List<WebElement> topics;
    
    @FindBy(id="searchMessage")
    private WebElement message;

    public List<WebElement> getTopics() {
        return topics;
    }

    public String getMessage() {
        return message.getText().trim();
    }
    
    
}
