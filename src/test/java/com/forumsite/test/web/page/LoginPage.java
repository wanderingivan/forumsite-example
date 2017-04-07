package com.forumsite.test.web.page;

import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.GrapheneElement;
import org.jboss.arquillian.graphene.page.Location;
import org.openqa.selenium.support.FindBy;

@Location("login.jsf")
public class LoginPage {
    
    @FindBy(id="loginForm:name")
    GrapheneElement username;
    
    @FindBy(id="loginForm:password")
    GrapheneElement password;
    
    @FindBy(id="loginForm:login")
    GrapheneElement login;
    
    @FindBy(id="logoutForm:logout")
    GrapheneElement logout;
    
    public void login(String username,String password){
        this.username.clear();
        this.password.clear();
        this.username.sendKeys(username);
        this.password.sendKeys(password);
        Graphene.guardHttp(login).click();
    }
    
    
    public void logout(){
        Graphene.guardHttp(logout).click();
    }
    
    public void loginIfNotAuthenticated(String username,String password){
        if(!this.login.isPresent()){ return; }
        login(username,password); 
    }
    
    public void logoutIfAuthenticated(){
        if(!this.logout.isPresent()){ return; }
        logout();
    }


    public boolean assertOnLoginPage() {
        return this.login.isPresent();
    }
}
