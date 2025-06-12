package se.johan.ituppgift.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.johan.ituppgift.model.LoginRequest;
import se.johan.ituppgift.service.TokenService;
import se.johan.ituppgift.exception.UserNotFoundException;

/**
 * Controller som hanterar inloggning och skickar ut JWT-token.
 *
 * Tar emot användarnamn och lösenord, kollar att det stämmer,
 * och ger tillbaka en token om allt är ok.
 */
@RestController
@RequestMapping("/request-token")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    /**
     * Skapar en controller med allt som behövs för att logga in och skapa token.
     *
     * @param authenticationManager Fixar själva inloggningen och kontrollerar användaren
     * @param tokenService Sköter skapandet av JWT-token
     */
    public AuthController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    /**
     * Tar emot inloggningsuppgifter och ger tillbaka en JWT-token om de stämmer.
     *
     * Om användaren inte finns eller lösenordet är fel kastas ett undantag.
     *
     * @param loginRequest Användarnamn och lösenord
     * @return JWT-token som en sträng
     * @throws UserNotFoundException Om inloggningen misslyckas
     */
    @PostMapping
    public ResponseEntity<String> token(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.username(),
                            loginRequest.password()
                    )
            );
            String token = tokenService.generateToken(authentication);
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            // Kan t.ex. vara fel användarnamn eller lösenord
            throw new UserNotFoundException("Användaren finns inte");
        }
    }
}
