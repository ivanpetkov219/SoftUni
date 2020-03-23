package com.spand0x.cardealer.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.spand0x.cardealer.models.dtos.CustomerPrintDto;
import com.spand0x.cardealer.models.entities.Customer;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        PropertyMap<Customer, CustomerPrintDto> customerMap = new PropertyMap<Customer, CustomerPrintDto>() {
            @Override
            protected void configure() {
                map().setBirthDay(null);
            }
        };
        modelMapper.addMappings(customerMap);
        return modelMapper;
    }

    @Bean
    public Gson gson() {
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();
    }
}
