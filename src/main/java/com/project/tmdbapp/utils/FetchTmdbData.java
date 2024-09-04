package com.project.tmdbapp.utils;

import org.springframework.beans.factory.annotation.Value;

public class FetchTmdbData {

    @Value("${ACCESS_TOKEN}")
    private String token;

    public FetchTmdbData() {

    }
}
