package com.dengo.erp.config;


import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * JacksonConfiguration
 * Configuration class for excluding lazy fields during json serialization
 *
 * @author Dmitry Sheremet
 */
@Configuration
public class JacksonConfiguration {

    @SuppressWarnings("unchecked")
    @Bean
    public Jackson2ObjectMapperBuilder configureObjectMapper() {
        return new Jackson2ObjectMapperBuilder()
                .modulesToInstall(Hibernate4Module.class);
    }

}