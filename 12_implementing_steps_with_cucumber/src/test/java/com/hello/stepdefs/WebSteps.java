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
        throw new io.cucumber.java.PendingException("Step not yet implemented");
    }

    @When("I set the {string} to {string}")
    public void iSetTheTo(String elementName, String textString) {
        throw new io.cucumber.java.PendingException("Step not yet implemented");
    }

    @When("I click the {string} button")
    public void iClickTheButton(String button) {
        throw new io.cucumber.java.PendingException("Step not yet implemented");
    }

    @Then("I should see the message {string}")
    public void iShouldSeeTheMessage(String message) {
        throw new io.cucumber.java.PendingException("Step not yet implemented");
    }

    @Then("I should see {string} in the results")
    public void iShouldSeeInTheResults(String name) {
        throw new io.cucumber.java.PendingException("Step not yet implemented");
    }

    @Then("I should not see {string} in the results")
    public void iShouldNotSeeInTheResults(String name) {
        throw new io.cucumber.java.PendingException("Step not yet implemented");
    }
}