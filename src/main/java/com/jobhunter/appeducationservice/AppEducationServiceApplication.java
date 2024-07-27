package com.jobhunter.appeducationservice;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AppEducationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppEducationServiceApplication.class, args);
	}

	@Bean
	public OpenAPI openAPI() {
		return new OpenAPI()
		.info(
				new Info()
						.title("education-service API")
						.description("This api was generated using springdoc for user-service app")
						.version("1.0")
						.contact(
								new Contact()
										.name("Safixon Abdusattorov")
										.email("safixongg@gmail.com")
						).license(
								new License()
										.name("Licence of API")
										.url("API licence url")
						)
		);
	}
}
