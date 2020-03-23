package com.spand0x.json.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.spand0x.json.utils.FileIOUtil;
import com.spand0x.json.utils.FileIOUtilImpl;
import com.spand0x.json.utils.ValidationUtil;
import com.spand0x.json.utils.ValidationUtilImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public Gson gson(){
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public ValidationUtil validationUtil(){
        return new ValidationUtilImpl();
    }

    @Bean
    public FileIOUtil fileIOUtil(){return new FileIOUtilImpl();
    }
}
