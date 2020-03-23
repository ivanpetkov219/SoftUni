package com.softuni.jsonprocessingexcars.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.softuni.jsonprocessingexcars.utils.ValidationUtil;
import com.softuni.jsonprocessingexcars.utils.ValidationUtilImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

@Configuration
public class ApplicationBeanConfiguration {
    @Bean
    public ValidationUtil validationUtil() {
        return new ValidationUtilImpl();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public Random random() {
        return new Random();
    }

    @Bean
    public Gson gson() {
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();
    }
    @Bean
    public BufferedReader bufferedReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }
}
