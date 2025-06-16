package se.johan.ituppgift.config;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

/**
 * Vi konfigurerar säkerheten för appen här.
 * <p>
 * Det vi gör är bland annat:
 * <ul>
 *     <li>Skapar ett RSA-nyckelpar som vi kan använda för att signera och verifiera JWTs.</li>
 *     <li>Ställer in hur vi kodar och avkodar JWT-token.</li>
 *     <li>Använder BCrypt för att hasha lösenord.</li>
 *     <li>Fixar en AuthenticationManager som använder DaoAuthenticationProvider.</li>
 *     <li>Ställer in hur JWTs scopes blir till roller utan prefix.</li>
 *     <li>Definierar vilka endpoint som kräver vilka roller och vilka som är öppna för alla.</li>
 *     <li>Stänger av CSRF och sätter sessionshantering till stateless eftersom vi kör JWT.</li>
 *     <li>Hantera fel när någon saknar rättigheter, så vi skickar ett tydligt meddelande.</li>
 * </ul>
 * </p>
 */
@Configuration
public class SecurityConfig {

    /**
     * Här skapar vi ett RSA-nyckelpar som vi använder för att signera våra JWT.
     *
     * @return ett RSA KeyPair som vi kan använda för signering och verifiering
     * @throws NoSuchAlgorithmException om RSA-algoritmen inte finns
     */
    @Bean
    public KeyPair keyPair() throws NoSuchAlgorithmException {
        var generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        return generator.generateKeyPair();
    }

    /**
     * Skapar en källa (JWKSource) med vårt RSA-nyckelpar så att Nimbus kan använda det för att signera token.
     *
     * @param keyPair nyckelparet vi genererat
     * @return källa med våra nycklar för JWT
     */
    @Bean
    public JWKSource<SecurityContext> jwkSource(KeyPair keyPair) {
        var rsaKey = new RSAKey.Builder((RSAPublicKey) keyPair.getPublic())
                .privateKey((RSAPrivateKey) keyPair.getPrivate())
                .keyID(UUID.randomUUID().toString())
                .build();
        var jwkSet = new JWKSet(rsaKey);
        return (jwkSelector, context) -> jwkSelector.select(jwkSet);
    }

    /**
     * Bean som vi använder för att skapa JWT-token (encode).
     *
     * @param jwkSource källa med våra signeringsnycklar
     * @return JwtEncoder som kodar token
     */
    @Bean
    public JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwkSource) {
        return new NimbusJwtEncoder(jwkSource);
    }

    /**
     * Bean för att validera och avkoda JWT-token (decode) med vår publika nyckel.
     *
     * @param keyPair RSA-nyckelparet
     * @return JwtDecoder som kan läsa token
     */
    @Bean
    public JwtDecoder jwtDecoder(KeyPair keyPair) {
        return NimbusJwtDecoder.withPublicKey((RSAPublicKey) keyPair.getPublic()).build();
    }

    /**
     * Lösenordshashning med BCrypt för att hålla lösenorden säkra.
     *
     * @return PasswordEncoder som hashar lösenord
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Skapar en AuthenticationManager som vi kan använda för att hantera inloggning.
     * Vi använder DaoAuthenticationProvider som i sin tur använder vår UserDetailsService
     * och PasswordEncoder för att validera användare.
     *
     * @param userDetailsService service som hämtar användardata
     * @param passwordEncoder för att kontrollera lösenord
     * @return AuthenticationManager som sköter autentisering
     */
    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        var provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(provider);
    }

    /**
     * Ställer in hur vi tar scopes från JWT och gör om dem till authorities utan prefix.
     * Det gör att vi kan använda "USER" eller "ADMIN" som roller utan "SCOPE_" framför.
     *
     * @return JwtAuthenticationConverter som hanterar denna konvertering
     */
    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        var grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        grantedAuthoritiesConverter.setAuthorityPrefix("");
        grantedAuthoritiesConverter.setAuthoritiesClaimName("scope");

        var authenticationConverter = new JwtAuthenticationConverter();
        authenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
        return authenticationConverter;
    }

    /**
     * Här sätter vi reglerna för vilka endpoints som ska vara öppna och vilka som kräver inloggning eller specifika roller.
     * Vi stänger av CSRF eftersom vi använder JWT och stateless session.
     * Vi hanterar fel för obehöriga med ett enkelt textmeddelande.
     *
     * @param http HttpSecurity som vi konfigurerar
     * @return SecurityFilterChain som vi använder i appen
     * @throws Exception om något går fel i konfigurationen
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/admin/**", "/registration/new","/delete/user").hasRole("ADMIN")
                        .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/public/**", "/request-token").permitAll()
                        .requestMatchers(
                                "/swagger-ui.html",
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/v3/apic-docs.yaml"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .exceptionHandling(exceptions -> exceptions
                        .accessDeniedHandler((request, response, exception) -> {
                            response.setContentType("text/plain");
                            response.setStatus(HttpStatus.FORBIDDEN.value());
                            response.getWriter().write("Du har inte behörighet!");
                        })
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter()))
                );

        return http.build();
    }
}
