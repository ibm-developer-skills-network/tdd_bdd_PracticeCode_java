package com.hello;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONObject;

public class IMDbService {
    private final String apiKey;

    public IMDbService(String apiKey) {
        this.apiKey = apiKey;
    }

    public JSONObject searchTitles(String title) {
        return fetchData("https://imdb-api.com/API/SearchTitle/" + apiKey + "/" + title);
    }

    public JSONObject movieReviews(String imdbId) {
        return fetchData("https://imdb-api.com/API/Reviews/" + apiKey + "/" + imdbId);
    }

    public JSONObject movieRatings(String imdbId) {
        return fetchData("https://imdb-api.com/API/Ratings/" + apiKey + "/" + imdbId);
    }

    private JSONObject fetchData(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                Scanner scanner = new Scanner(url.openStream());
                StringBuilder inline = new StringBuilder();
                while (scanner.hasNext()) {
                    inline.append(scanner.nextLine());
                }
                scanner.close();
                return new JSONObject(inline.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new JSONObject();  // Return empty JSON object on failure
    }
}
