package com.example.equipo_futbol;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
		title = "API de Fútbol",
		version = "1.0",
		description = "API para administrar equipos, jugadores, partidos y estadísticas de fútbol"
))
public class EquipoFutbolApplication {

	public static void main(String[] args) {
		SpringApplication.run(EquipoFutbolApplication.class, args);
	}
}