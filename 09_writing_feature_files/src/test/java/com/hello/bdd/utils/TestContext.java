package com.hello.bdd.utils;

import io.cucumber.spring.ScenarioScope;
import org.springframework.stereotype.Component;
import org.openqa.selenium.WebDriver;

@Component
@ScenarioScope
public class TestContext {
    private String baseUrl;
    private int waitSeconds;
    private WebDriver driver;
    
    public String getBaseUrl() {
        return baseUrl;
    }
    
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
    
    public int getWaitSeconds() {
        return waitSeconds;
    }
    
    public void setWaitSeconds(int waitSeconds) {
        this.waitSeconds = waitSeconds;
    }
    
    public WebDriver getDriver() {
        return driver;
    }
    
    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }
}