package se.johan.ituppgift.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

@Service
public class TokenService {
    public JwtEncoder jwtEncoder;
    private LoggingComponent loggingComponent;

    public TokenService(JwtEncoder jwtEncoder, LoggingComponent loggingComponent) {
        this.jwtEncoder = jwtEncoder;
        this.loggingComponent = loggingComponent;
    }

    public String generateToken(Authentication authentication) {
        Instant now = Instant.now();

        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.HOURS))
                .subject(authentication.getName())
                .claim("scope", scope)
                .build();
        loggingComponent.logInfo("Generated token: {" + claims + "}");

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
