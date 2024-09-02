package com.project.tmdbapp.controller;

import com.project.tmdbapp.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
public class TmdbController {

    @Autowired
    public TmdbController(MovieService movieService) {
    }

    public void authenticate() {
        String token = "tmdb-access-token";
        String path = Paths.get(System.getProperty("user.dir")).getParent().toString();
        try (
                BufferedReader br = Files.newBufferedReader(Paths.get(path + "/" + token))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(path);
    }

}
