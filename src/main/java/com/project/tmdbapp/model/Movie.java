package com.project.tmdbapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
//@Entity
//@Table(name = "movie")
public class Movie {

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "movie_id")
//    private UUID movieId;

    private String title;

    private String overview;

    public Movie() {}

    public Movie(String title, String overview) {
        this.title = title;
        this.overview = overview;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", overview='" + overview + '\'' +
                '}';
    }

}
