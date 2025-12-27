package com.example.blog_app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
	
	
	@Bean
	public WebMvcConfigurer corConfigurer() {
		return new WebMvcConfigurer() {
			
			public void addCorsMapping(CorsRegistry registry) {
				registry.addMapping("/**")
				.allowedOrigins("http://localhost:5178")
				.allowedMethods("GET","POST","PUT","DELETE","OPTIONS")
				.allowedHeaders("*")
				.allowCredentials(true);
			}
			
		};
	}
}
