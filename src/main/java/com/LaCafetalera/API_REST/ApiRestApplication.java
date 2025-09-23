package com.LaCafetalera.API_REST;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ApiRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiRestApplication.class, args);
	}


	@GetMapping("/hello")  // <- Agregar este método
	public String hello() {
		return "Aplicación Spring Boot funcionando correctamente";
	}
}
