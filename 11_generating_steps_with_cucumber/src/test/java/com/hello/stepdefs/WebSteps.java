package com.hello.stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

public class WebSteps {
    private static final String ID_PREFIX = "pet_";
    private final TestContext context;
    private final WebDriver driver;

    public WebSteps(TestContext context) {
        this.context = context;
        this.driver = context.getDriver();
    }

    
}