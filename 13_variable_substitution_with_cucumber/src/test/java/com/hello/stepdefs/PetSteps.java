package com.hello.stepdefs;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hello.model.Pet;
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

    // List all pets and delete them one by one
@Given("the following pets")
public void givenTheFollowingPets(DataTable dataTable) {
    // List all pets and delete them one by one
Response response = given()
        .contentType(ContentType.JSON)
        .when()
        .get(context.getBaseUrl() + "/pets");

assertThat(response.getStatusCode()).isEqualTo(200);

List<Pet> existingPets;
try {
    existingPets = new ObjectMapper().readValue(
            response.asString(),
            new TypeReference<List<Pet>>() {}
    );
    
    // Delete each pet
    for (Pet pet : existingPets) {
        Response deleteResponse = given()
                .contentType(ContentType.JSON)
                .when()
                .delete(context.getBaseUrl() + "/pets/" + pet.getId());
        
        assertThat(deleteResponse.getStatusCode()).isEqualTo(204);
    }
} catch (Exception e) {
    throw new RuntimeException("Error processing existing pets", e);
}

    // Load the database with new pets
    List<Map<String, String>> rows = dataTable.asMaps();
    for (Map<String, String> row : rows) {
        // Create payload for the POST request
        Map<String, Object> payload = new HashMap<>();
        payload.put("name", row.get("name"));
        payload.put("category", row.get("category"));

        // Convert "available" string to boolean
        String availableStr = row.get("available");
        boolean isAvailable = "True".equalsIgnoreCase(availableStr) || 
                             "true".equalsIgnoreCase(availableStr) || 
                             "1".equals(availableStr);
        payload.put("available", isAvailable);

        payload.put("gender", row.get("gender"));
        payload.put("birthday", row.get("birthday"));

        // Post the new pet to the API
        Response postResponse = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post(context.getBaseUrl() + "/pets");

        assertThat(postResponse.getStatusCode()).isEqualTo(201);
    }

}
}