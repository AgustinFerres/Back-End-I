package com.example.proyecto_Integrador;

import com.example.proyecto_Integrador.dao.BD;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProyectoIntegradorApplication {

	public static void main(String[] args) {
		BD.crearTablas();
		SpringApplication.run(ProyectoIntegradorApplication.class, args);
	}

}
