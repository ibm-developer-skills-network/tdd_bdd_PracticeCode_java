package com.hello.bdd.steps;

import com.hello.bdd.utils.TestContext;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class NavigationSteps {
    private final TestContext testContext;
    
    public NavigationSteps(TestContext testContext) {
        this.testContext = testContext;
    }
    
    @Given("I navigate to the home page")
    public void iNavigateToTheHomePage() {
        WebDriver driver = testContext.getDriver();
        driver.get(testContext.getBaseUrl());
        
        // Use the wait seconds from the environment
        WebDriverWait wait = new WebDriverWait(driver, 
                Duration.ofSeconds(testContext.getWaitSeconds()));
    }
}