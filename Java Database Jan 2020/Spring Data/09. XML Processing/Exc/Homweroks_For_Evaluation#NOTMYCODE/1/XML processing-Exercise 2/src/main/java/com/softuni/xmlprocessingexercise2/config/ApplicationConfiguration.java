package com.softuni.xmlprocessingexercise2.config;

import com.softuni.xmlprocessingexercise2.utils.XMLParserImpl;
import com.softuni.xmlprocessingexercise2.utils.enums.Discount;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public com.softuni.xmlprocessing.utils.XMLParser xmlParser(){

        return new XMLParserImpl();
    }

    @Bean
    public  ModelMapper modelMapper(){
        return  new ModelMapper();
    }


}
