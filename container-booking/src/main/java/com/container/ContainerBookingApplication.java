package com.container;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

/**
 * ContainerBookingApplication
 * @author santhoshkumardurairaj
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.container","com.container.repository", "com.container.model","com.container.config","com.container.exception,com.container.service"})
public class ContainerBookingApplication {
	
	/**
	 * RestTemplate bean
	 * @return
	 */
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}

	/**
	 * Main method
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(ContainerBookingApplication.class, args);
	}

}
