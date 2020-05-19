package com.mindtree.sellyourfurniture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
@SpringBootApplication
public class SellYourFurnitureApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SellYourFurnitureApplication.class, args);
	}
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SellYourFurnitureApplication.class);
    }

}
