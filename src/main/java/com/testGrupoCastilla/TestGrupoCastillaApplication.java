package com.testGrupoCastilla;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Gestión de Detalles y Maestros - API REST",
				description = "Documentación de la API REST para gestionar entidades Maestro y Detalle",
				version = "v1",
				contact = @Contact(
						name = "Equipo de Desarrollo - Grupo Castilla",
						email = "soporte@grupocastilla.com",
						url = "https://www.grupocastilla.com"
				),
				license = @License(
						name = "Licencia Apache 2.0",
						url = "https://www.apache.org/licenses/LICENSE-2.0"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Documentación Swagger de la API",
				url = "http://localhost:8080/swagger-ui.html"
		)
)
public class TestGrupoCastillaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestGrupoCastillaApplication.class, args);
	}
}
