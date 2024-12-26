package com.sistemas.karnaval.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

	@Bean
	OpenAPI customOpenAPI() {
		return new OpenAPI().info(new Info().title("API de Reseñas y Opiniones").version("1.0")
				.description("Documentación de la API para la gestión de reseñas y opniones"));
	}
}
