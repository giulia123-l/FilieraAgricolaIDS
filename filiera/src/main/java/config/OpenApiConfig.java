package config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(title = "Filiera Agricola - API (Iterazione 1)", version = "1.0.0",
                 description = "API per registrazione, approvazione utenti e login"),
    servers = { @Server(url = "http://localhost:8080") }
)
public class OpenApiConfig {}
