package com.rasgrass.dira;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

/**
 *
 * @author RasGrass
 */
@ComponentScan
@Configuration
@EnableAutoConfiguration
@Import(RepositoryRestMvcConfiguration.class)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}
}
