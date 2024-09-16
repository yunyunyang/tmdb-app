package com.project.tmdbapp.enums;

import lombok.Getter;

public class MovieEnums {

    @Getter
    public enum ImageSize {
        SMALL("https://image.tmdb.org/t/p/w185"),
        MEDIUM("https://image.tmdb.org/t/p/w300"),
        LARGE("https://image.tmdb.org/t/p/w500"),
        ORIGINAL("https://image.tmdb.org/t/p/original");

        private final String url;

        ImageSize(String url) {
            this.url = url;
        }
    }

    public enum Genre {

    }
}
