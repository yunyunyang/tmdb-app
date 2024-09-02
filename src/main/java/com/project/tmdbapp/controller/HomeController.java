package com.project.tmdbapp.controller;

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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    @Value("${ACCESS_TOKEN}")
    private String token;

    private static final String BASE_URL = "https://api.themoviedb.org/3/movie/";

    @GetMapping
    public String index(Model model) {

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

                List<Movie> movies = objectMapper.readValue(resultsString, new TypeReference<List<Movie>>() {});
                model.addAttribute("movies", movies);

//                for (int i = 0; i < results.length(); i++) {
//                    JSONObject movie = results.getJSONObject(i);
//                    String title = movie.getString("title");
//                    String overview = movie.getString("overview");
//                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        model.addAttribute("name", "World");

        return "index";
    }

    @RequestMapping("/welcome")
    @ResponseBody
    public ModelAndView modelRenderPage() {
        Map<String, String> model = new HashMap<>();
        model.put("data", "Welcome to TMDB");
        ModelAndView mv = new ModelAndView();
//        view.setViewName("home");
//        view.getModel().putIfAbsent("welcome", "Hello World");
        return mv;
    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello";
    }
}
