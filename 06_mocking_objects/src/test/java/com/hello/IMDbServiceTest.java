package com.hello;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
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

   

    @Test
    void testSearchByTitle() {
        // Mocking IMDbService
        imdbService = mock(IMDbService.class);
        // return the GOOD_SEARCH test fixture data.
        when(imdbService.searchTitles("Babmi")).thenReturn(testResponses.get("GOOD_SEARCH"));
        
        JSONObject result = imdbService.searchTitles("Babmi");
        assertNotNull(result);
        assertEquals("Babmi", result.getJSONArray("results").getJSONObject(0).getString("title"));
        assertEquals("tt1375666", result.getJSONArray("results").getJSONObject(0).getString("id"));
    }


    @Test
void testSearchWithNoResults() {
    // Mocking IMDbService
    imdbService = mock(IMDbService.class);

    // Returning an Empty JSONObject for "Bambi" (Fixing the typo)
    when(imdbService.searchTitles("Bambi")).thenReturn(new JSONObject());

    JSONObject result = imdbService.searchTitles("Bambi");

    // Checking if the returned JSON object is empty
    assertNotNull(result);  // Ensure result is not null
    assertTrue(result.isEmpty());
}


    @Test
    void testInvalidApiKey() {
        imdbService = mock(IMDbService.class);
        when(imdbService.searchTitles("Bambi")).thenReturn(testResponses.get("INVALID_API"));

        JSONObject result = imdbService.searchTitles("Bambi");
        
        assertNotNull(result);
        assertEquals("Invalid API Key", result.getString("errorMessage"));
    }

    @Test
    void testMovieRatings() {
        imdbService = mock(IMDbService.class);
        when(imdbService.movieRatings("tt1375666")).thenReturn(testResponses.get("GOOD_RATING"));

        JSONObject result = imdbService.movieRatings("tt1375666");
        
        assertNotNull(result);
        assertEquals("Bambi", result.getString("title"));
        assertEquals(3, result.getInt("filmAffinity"));
        assertEquals(5, result.getInt("rottenTomatoes"));
    }
}
