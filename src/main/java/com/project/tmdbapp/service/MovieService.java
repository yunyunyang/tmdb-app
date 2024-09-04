package com.project.tmdbapp.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.tmdbapp.model.Movie;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    @Value("${ACCESS_TOKEN}")
    private String token;

    private static final String BASE_URL = "https://api.themoviedb.org/3/movie/";

    public List<Movie> fetchTrendingMovie() {
        List<Movie> movies = new ArrayList<>();
        try {
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(BASE_URL + "now_playing?language=en-US&page=1")
                    .get()
                    .addHeader("accept", "application/json")
                    .addHeader("Authorization", "Bearer " + token)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                }

                // Convert response body to JSON object
                String responseBody = response.body().string();
                JSONObject jsonObject = new JSONObject(responseBody);

                // Accessing data from the JSON object
                int page = jsonObject.getInt("page");
                int totalPages = jsonObject.getInt("total_pages");
                int totalResults = jsonObject.getInt("total_results");

                // Extract the array of movies
                JSONArray results = jsonObject.getJSONArray("results");

                // Convert JSONArray to String
                String resultsString = results.toString();

                // Create an ObjectMapper instance
                ObjectMapper objectMapper = new ObjectMapper();

                // Customize ObjectMapper behavior
                objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

                movies = objectMapper.readValue(resultsString, new TypeReference<List<Movie>>() {});

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movies;
    }
}
