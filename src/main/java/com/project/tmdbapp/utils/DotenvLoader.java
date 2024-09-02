package com.project.tmdbapp.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DotenvLoader {

    public static void loadEnv() {
        try {
            FileInputStream fis = new FileInputStream(".env");
            Properties props = new Properties();
            props.load(fis);

            props.forEach((key, value) -> {
                String propertyKey = key.toString();
                String propertyValue = value.toString();
                System.setProperty(propertyKey, propertyValue);
            });
            fis.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
