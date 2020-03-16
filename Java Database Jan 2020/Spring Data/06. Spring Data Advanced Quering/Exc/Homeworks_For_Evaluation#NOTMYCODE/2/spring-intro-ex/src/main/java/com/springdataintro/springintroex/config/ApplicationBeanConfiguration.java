package com.springdataintro.springintroex.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public BufferedReader bufferedReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }

//    @Bean
//    public PreparedStatement preparedStatement() {
//        return new PreparedStatement();
//    }
}
