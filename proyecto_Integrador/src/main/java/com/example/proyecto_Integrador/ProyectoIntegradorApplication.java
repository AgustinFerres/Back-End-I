package com.example.proyecto_Integrador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class ProyectoIntegradorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoIntegradorApplication.class, args);
	}
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/").allowedOrigins("http://127.0.0.1:5500");
				registry.addMapping("/odontologos").allowedOrigins("http://127.0.0.1:5500");
				registry.addMapping("/odontologos/buscar").allowedOrigins("http://127.0.0.1:5500");
				registry.addMapping("/pacientes").allowedOrigins("http://127.0.0.1:5500");
				registry.addMapping("/pacientes/buscar").allowedOrigins("http://127.0.0.1:5500");
				registry.addMapping("/turnos").allowedOrigins("http://127.0.0.1:5500");
				registry.addMapping("/turnos/buscar").allowedOrigins("http://127.0.0.1:5500");
			}
		};
	}
}
