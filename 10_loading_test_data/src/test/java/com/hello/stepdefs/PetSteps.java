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

    // Add your step implementation here
}