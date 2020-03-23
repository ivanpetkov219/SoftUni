package com.softuni.jsonprocessingex.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.softuni.jsonprocessingex.utils.ValidationUtil;
import com.softuni.jsonprocessingex.utils.ValidationUtilImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public Gson gson() {
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();
    }
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public ValidationUtil validationUtil(){
        return new ValidationUtilImpl();
    }

    @Bean
    public BufferedReader bufferedReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }
}
