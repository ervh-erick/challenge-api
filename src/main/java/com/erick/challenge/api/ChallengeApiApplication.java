package com.erick.challenge.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Challenge API", version = "1.0.0", description = "API to manage users and their cars"))
public class ChallengeApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallengeApiApplication.class, args);
	}
}
