package com.cardenas.rest.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
        corsRegistry.addMapping("/**")
                // Set the domain name that allows cross-domain requests
                .allowedOriginPatterns("*")
                // Set the allowed methods
                .allowedMethods("*")

                // Allowed time across domains
                .maxAge(3600L)
                .allowedHeaders("*")
                .exposedHeaders("Authorization")
                // whether to allow certificates (cookies)
                .allowCredentials(true);
    }
}