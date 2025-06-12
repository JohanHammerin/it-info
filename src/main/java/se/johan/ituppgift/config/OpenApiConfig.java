package se.johan.ituppgift.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

/**
 * Konfiguration för OpenAPI/Swagger-dokumentation av API:t.
 * <p>
 * Definierar API-titel och version samt global säkerhetskrav
 * för JWT-baserad autentisering.
 * </p>
 *
 * <p>
 * SecurityScheme definierar:
 * <ul>
 *   <li>Typ: HTTP Bearer</li>
 *   <li>Scheme: "bearer"</li>
 *   <li>BearerFormat: "JWT"</li>
 * </ul>
 * </p>
 *
 * <p>
 * SecurityRequirement kopplar "bearerAuth" till alla endpoints
 * för att kräva autentisering via JWT som standard.
 * </p>
 */
@OpenAPIDefinition(
        info = @Info(title = "API med JWT", version = "1.0"),
        security = @SecurityRequirement(name = "bearerAuth")
)
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class OpenApiConfig {
}
