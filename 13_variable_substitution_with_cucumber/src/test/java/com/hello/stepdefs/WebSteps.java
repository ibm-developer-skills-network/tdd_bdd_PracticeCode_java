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

    @Given("I am on the {string}")
    public void iAmOnThe(String pageName) {
        driver.get(context.getBaseUrl());
    }

    @When("I set the {string} to {string}")
    public void iSetTheTo(String elementName, String textString) {
        String elementId = ID_PREFIX + elementName.toLowerCase().replace(" ", "_");
        WebElement element = driver.findElement(By.id(elementId));
        element.clear();
        element.sendKeys(textString);
    }

    @When("I click the {string} button")
    public void iClickTheButton(String button) {
        String buttonId = button.toLowerCase() + "-btn";
        WebElement element = driver.findElement(By.id(buttonId));
        element.click();
    }

    @Then("I should see the message {string}")
    public void iShouldSeeTheMessage(String message) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(context.getWaitSeconds()));
        boolean found = wait.until(
                ExpectedConditions.textToBePresentInElementLocated(By.id("flash_message"), message)
        );
        assertThat(found).isTrue();
    }

    @Then("I should see {string} in the results")
    public void iShouldSeeInTheResults(String name) {
        // First, wait for the search_results element to be visible/present
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(context.getWaitSeconds()));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search_results")));
        
        // Get the element and check if it contains the text
        WebElement element = driver.findElement(By.id("search_results"));
        String resultsText = element.getText();
        
        // Assert that the name is in the results text
        assertThat(resultsText).contains(name);
    }

    @Then("I should not see {string} in the results")
    public void iShouldNotSeeInTheResults(String name) {
        WebElement element = driver.findElement(By.id("search_results"));
        String resultsText = element.getText();
        assertThat(resultsText.contains(name))
                .isFalse();
    }
}