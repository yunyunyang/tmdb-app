package com.project.tmdbapp.model;

import com.project.tmdbapp.enums.MovieEnums;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
//@Entity
//@Table(name = "movie")
public class Movie {

    public static final String BASE_URL = "https://api.themoviedb.org/3/movie/";

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "movie_id")
//    private UUID movieId;

    private String title;

    private String overview;

    // Size: https://www.themoviedb.org/talk/5ee4ba52a217c0001fd0cb83
    // https://image.tmdb.org/t/p/w92 + poster_path
    private String poster_path;

    private Date release_date;

    public Movie() {}

    public Movie(String title, String overview) {
        this.title = title;
        this.overview = overview;
    }

    public String getSmallPoster() {
        return MovieEnums.ImageSize.SMALL.getUrl() +  poster_path;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", overview='" + overview + '\'' +
                ", poster_path='" + getSmallPoster() + '\'' +
                '}';
    }

}
