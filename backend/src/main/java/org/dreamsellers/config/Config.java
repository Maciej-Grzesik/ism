package org.dreamsellers.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@Configuration
public class Config {

    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
