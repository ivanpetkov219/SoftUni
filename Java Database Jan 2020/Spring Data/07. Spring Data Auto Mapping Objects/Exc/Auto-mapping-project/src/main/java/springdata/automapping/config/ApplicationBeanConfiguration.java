package springdata.automapping.config;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springdata.automapping.utils.ValidationUtil;
import springdata.automapping.utils.ValidationUtilImpl;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public BufferedReader bufferedReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }

    @Bean
    public ModelMapper modelMapper (){
        return new ModelMapper();
    }

    @Bean
    public ValidationUtil validationUtil(){
        return new ValidationUtilImpl();
    }

}
