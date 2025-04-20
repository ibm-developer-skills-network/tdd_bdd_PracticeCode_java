package com.hello.stepdefs;

import io.cucumber.java.en.Given;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.cucumber.datatable.DataTable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class PetSteps {
    private final TestContext context;

    public PetSteps(TestContext context) {
        this.context = context;
    }

    @Given("the following pets")
    public void theFollowingPets(DataTable dataTable) {
        // Store the data table for later use in the context
        context.setPetDataTable(dataTable);
        
        // You would typically send this data to your API to create the test pets
        // For now we'll just store it in the context
    }
}