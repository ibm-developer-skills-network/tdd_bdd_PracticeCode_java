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

/**
 * Web Steps
 * Steps file for web interactions with Selenium
 * For information on waiting until elements are present in the HTML see:
 * https://www.selenium.dev/documentation/webdriver/waits/
 */
public class WebSteps {
    private static final String ID_PREFIX = "pet_";
    private final TestContext context;
    private final WebDriver driver;

    public WebSteps(TestContext context) {
        this.context = context;
        this.driver = context.getDriver();
    }

    @Given("I am on the \"Home Page\"")
    public void iAmOnTheHomePage() {
        driver.get(context.getBaseUrl());
    }

    @When("I set the \"Category\" to \"dog\"")
    public void iSetTheCategoryToDog() {
        WebElement element = driver.findElement(By.id("pet_category"));
        element.clear();
        element.sendKeys("dog");
    }

    @When("I click the \"Search\" button")
    public void iClickTheSearchButton() {
        WebElement element = driver.findElement(By.id("search-btn"));
        element.click();
    }

    @Then("I should see the message \"Success\"")
    public void iShouldSeeTheMessageSuccess() {
        WebElement element = driver.findElement(By.id("flash_message"));
        assertThat(element.getText()).contains("Success");
    }

    @Then("I should see \"Fido\" in the results")
    public void iShouldSeeFidoInTheResults() {
        WebElement element = driver.findElement(By.id("search_results"));
        assertThat(element.getText()).contains("Fido");
    }

    @Then("I should not see \"Kitty\" in the results")
    public void iShouldNotSeeKittyInTheResults() {
        WebElement element = driver.findElement(By.id("search_results"));
        assertThat(element.getText()).doesNotContain("Kitty");
    }

    @Then("I should not see \"Leo\" in the results")
    public void iShouldNotSeeLeoInTheResults() {
        WebElement element = driver.findElement(By.id("search_results"));
        assertThat(element.getText()).doesNotContain("Leo");
    }
}