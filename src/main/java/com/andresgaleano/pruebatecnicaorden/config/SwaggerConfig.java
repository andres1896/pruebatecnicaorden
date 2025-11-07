package com.andresgaleano.pruebatecnicaorden.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI apiInfo() {
		return new OpenAPI().info(new Info().title("API de Órdenes de productos").version("1.0.0").description(
				"API REST desarrollada en **Spring Boot (Java 11)** para registrar y consultar órdenes de clientes. "
						+ "Incluye endpoints para crear y consultar órdenes, con integración a base de datos MySQL.")
				.contact(new Contact().name("Andres Galeano").email("andresgaleano96@gmail.com")
						.url("https://github.com/andres1896")));
	}
}