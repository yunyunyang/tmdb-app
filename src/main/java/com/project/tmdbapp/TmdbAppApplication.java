package com.project.tmdbapp;

import com.project.tmdbapp.utils.DotenvLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

// Added exclude = {DataSourceAutoConfiguration.class} to disable Spring data auto configuration
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class TmdbAppApplication {

    public static void main(String[] args) {
        DotenvLoader.loadEnv();

        SpringApplication.run(TmdbAppApplication.class, args);
        System.out.println("Hello World!");
    }

}
