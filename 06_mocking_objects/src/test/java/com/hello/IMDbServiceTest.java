package com.hello;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IMDbServiceTest {
    
    private static Map<String, JSONObject> testResponses;
    private IMDbService imdbService;
    
    @BeforeAll
    static void loadTestFixtures() throws Exception {
    String json = new String(Files.readAllBytes(Paths.get("src/test/resources/fixtures/imdb_responses.json")));
    JSONObject jsonData = new JSONObject(json);

    // Convert Map<String, Object> to Map<String, JSONObject>
    testResponses = new HashMap<>();
    for (String key : jsonData.keySet()) {
        testResponses.put(key, jsonData.getJSONObject(key));
    }
}

    @BeforeEach
    void setUp() {
        imdbService = mock(IMDbService.class);
    }

   //   T E S T   C A S E S
}
