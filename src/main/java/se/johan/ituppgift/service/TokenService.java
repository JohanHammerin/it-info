package se.johan.ituppgift.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import se.johan.ituppgift.LoggingComponent;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

/**
 * Service för att generera JWT-token baserat på autentisering.
 */
@Service
public class TokenService {

    private final JwtEncoder jwtEncoder; // Komponent som ansvarar för att koda JWT-token
    private final LoggingComponent loggingComponent; // Egen komponent för loggning

    /**
     * Konstruktor för TokenService.
     *
     * @param jwtEncoder       JWT-kodare för att skapa token.
     * @param loggingComponent Komponent för loggning.
     */
    public TokenService(JwtEncoder jwtEncoder, LoggingComponent loggingComponent) {
        this.jwtEncoder = jwtEncoder;
        this.loggingComponent = loggingComponent;
    }

    /**
     * Genererar en JWT-token baserat på användarens autentisering.
     * Token innehåller användarens namn som subject och roller som "scope".
     * Token är giltig i 1 timme från skapandet.
     *
     * @param authentication Objekt som innehåller autentiseringsinformation.
     * @return JWT-token som en sträng.
     */
    public String generateToken(Authentication authentication) {
        Instant now = Instant.now(); // Hämtar nuvarande tid för token-claims

        // Slår ihop alla användarroller (authorities) till en mellanslagsseparerad sträng
        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        // Skapar claimset för JWT-token med issuer, issuedAt, expiresAt, subject och scope
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self") // Token utfärdas av denna applikation
                .issuedAt(now) // Tidpunkt då token skapades
                .expiresAt(now.plus(1, ChronoUnit.HOURS)) // Token giltig i 1 timme
                .subject(authentication.getName()) // Användarnamn som token gäller för
                .claim("scope", scope) // Roller kopplade till användaren
                .build();

        // Loggar att token genererats med claimset (kan innehålla känslig data)
        loggingComponent.logInfo("Generated token: {" + claims + "}");

        // Kodar claimset till en JWT-token som returneras som sträng
        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
