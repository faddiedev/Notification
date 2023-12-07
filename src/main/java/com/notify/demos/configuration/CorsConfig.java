package com.notify.demos.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
	private static final Logger logger = LogManager.getLogger(CorsConfig.class);
	
	    @Bean
	    public WebMvcConfigurer corsConfigurer() {
	    	logger.info("start");
	        return new WebMvcConfigurer() {
	            @Override
	            public void addCorsMappings(CorsRegistry registry) {
	                registry.addMapping("/**")
                    .allowedOrigins("http://localhost:8080") // Replace with your frontend's origin
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                    .allowedHeaders("Content-Type", "Authorization") // Add other required headers
                    .exposedHeaders("Authorization"); // Expose any additional headers if needed
	            }
	        };
	    }
	}
	