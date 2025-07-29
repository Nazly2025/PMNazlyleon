package com.pruebameli.pruebameli_backend.conf;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Configuración de OpenAPI/Swagger para documentar la API REST
 * Accede a la documentación en: http://localhost:8080/swagger-ui.html
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Productos - Prueba para el proceso de selección en Mercado Libre")
                        .description("API REST para la gestión de productos en un e-commerce. " +
                                   "Permite consultar los datos del productos, vendedor, reseñas asociadas al producto., imágenes " +
                                   "Este desarrollado corresponde a la prueba técnica para el proceso de selección en Mercado Libre.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Equipo de Desarrollo")
                                .email("dev@pruebameli.com")
                                .url("https://github.com/pruebameli"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8080")
                                .description("Servidor de desarrollo"),
                        new Server()
                                .url("https://api.pruebameli.com")
                                .description("Servidor de producción")
                ));
    }
} 