package com.hello.bdd.hooks;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import com.hello.bdd.utils.TestContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;

public class Hooks {
    private final TestContext testContext;
    
    public Hooks(TestContext testContext) {
        this.testContext = testContext;
    }
    
    @Before("@cucumber")
    public void beforeAll() {
        // Initialize WebDriver
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        testContext.setDriver(driver);
        
        
    }
    
    @After("@cucumber")
    public void afterAll() {
        if (testContext.getDriver() != null) {
            testContext.getDriver().quit();
        }
    }
}