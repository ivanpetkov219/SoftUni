package com.softuni.xmlparsingexerciseone.config;


import com.softuni.xmlparsingexerciseone.dtos.firstExerciseDtos.ProductDto;
import com.softuni.xmlparsingexerciseone.entities.Product;
import com.softuni.xmlparsingexerciseone.utils.ValidationUtil;
import com.softuni.xmlparsingexerciseone.utils.ValidationUtilImpl;
import com.softuni.xmlparsingexerciseone.utils.XMLParser;
import com.softuni.xmlparsingexerciseone.utils.XMLParserImpl;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.print.attribute.standard.Destination;


@Configuration
public class ApplicationConfig {

    @Bean
    public  ModelMapper modelMapper(){

        ModelMapper mapper=new ModelMapper();

        return mapper;
    }

    @Bean
    public XMLParser xmlParser(){

        return new XMLParserImpl();
    }

    @Bean
    public ValidationUtil validationUtil(){
        return new ValidationUtilImpl();
    }


}
