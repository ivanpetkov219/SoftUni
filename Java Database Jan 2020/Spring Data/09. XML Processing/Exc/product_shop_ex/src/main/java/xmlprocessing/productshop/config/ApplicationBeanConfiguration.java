package xmlprocessing.productshop.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xmlprocessing.productshop.utils.ValidatorUtil;
import xmlprocessing.productshop.utils.ValidatorUtilImpl;
import xmlprocessing.productshop.utils.XmlParser;
import xmlprocessing.productshop.utils.XmlParserImpl;

import javax.validation.Validation;
import javax.xml.validation.Validator;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public XmlParser xmlParser(){
        return new XmlParserImpl();
    }

    @Bean
    public ValidatorUtil validationUtil() {
        return new ValidatorUtilImpl();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public Random random(){
        return new Random();
    }

    @Bean
    public BufferedReader bufferedReader(){
        return new BufferedReader(new InputStreamReader(System.in));
    }
}
