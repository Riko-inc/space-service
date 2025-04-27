package org.example.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition (servers = {
        @Server(url = "http://${SERVER_IP:localhost}:${SERVER_HTTP_PORT:8085}${SWAGGER_PREFIX:}", description = "Server")},
        info = @Info(
                title = "Сервис рабочих пространств",
                description = "А я Максу помогаю", version = "1.0.0",
                contact = @Contact(
                        name = "Черников Антон",
                        url = "https://t.me/tyques"
                )
        )
)
@SecurityScheme(
        name = "JWT",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer")
public class OpenApiConfig {

}