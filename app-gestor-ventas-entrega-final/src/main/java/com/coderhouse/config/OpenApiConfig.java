package com.coderhouse.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("API REST Full | Gestor de Ventas")
                        .version("1.0.0")
                        .description("La API REST de Gestion de Ventas proporciona endpoints para administrar " +
                                "las operaciones de Venta, los Clientes, y los Productos (CRUD: Crear, Leer, Actualizar, Eliminar), " +
                                "todo esto en el marco del Proyecto Final del Curso \"Programación con Java\" de la plataforma CoderHouse. " +
                                "La API está documentada utilizando Swagger, lo que facilita la comprensión de los endpoints y su uso.")
                        .contact(new Contact()
                                .name("Rocío Ahumada")
                                .email("roahumada89@gmail.com")
                                .url("https://github.com/RocioAhumada89"))
                )
                .servers(List.of(new Server()
                        .url("http://localhost:8080")
                        .description("Servidor Local")));

    }


}
