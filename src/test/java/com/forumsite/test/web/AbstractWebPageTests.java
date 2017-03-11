package com.forumsite.test.web;

import java.net.URL;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.openqa.selenium.WebDriver;

public class AbstractWebPageTests {

    @Drone
    WebDriver browser;
    
    @ArquillianResource
    URL deploymentUrl;
    
}
