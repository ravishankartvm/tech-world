package com.learning.techworld.PolicyEventProducer;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Policy Event Producer API", version = "1.0", description = "Policy Producer Details"))
public class PolicyEventProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PolicyEventProducerApplication.class, args);
	}

}
