package com.forumsite.test.web.page;

import org.jboss.arquillian.graphene.Graphene;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchForm {

    @FindBy(id="searchForm:query")
    private WebElement queryInput;
    
    @FindBy(id="searchForm:search")
    private WebElement searchBtn;
    
    @FindBy(id="searchForm:useCategory")
    private WebElement useCatCbx;
    
    public void searchThreads(String query){
        this.queryInput.clear();
        this.queryInput.sendKeys(query);
        Graphene.guardHttp(searchBtn).click();
    }
    
    public void searchThreadsCategory(String query){
        this.queryInput.clear();
        this.queryInput.sendKeys(query);
        this.useCatCbx.click();
        /*if(!useCatCbx.isSelected()){
        }*/
        Graphene.guardHttp(searchBtn).click();
    }

}
