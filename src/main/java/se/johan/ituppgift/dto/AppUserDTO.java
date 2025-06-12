package se.johan.ituppgift.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

/**
 * DTO för att ta emot användarens registreringsdata.
 *
 * Innehåller validering för varje fält.
 */
public class AppUserDTO {

    /**
     * Användarnamn – får inte vara tomt.
     */
    @NotBlank(message = "Användarnamnet får inte vara tomt")
    private String username;

    /**
     * Lösenord – minst 8 tecken, 1 stor bokstav, 2 siffror, 2 specialtecken.
     */
    @NotBlank(message = "Lösenordet får inte vara tomt")
    @Schema(
            description = "Lösenordet måste vara minst 8 tecken, 1 stor bokstav, 2 siffror och 2 specialtecken",
            example = "string"
    )
    @Pattern(
            regexp = "^(?=(?:.*\\d){2,})(?=(?:.*[A-ZÅÄÖ]){1,})(?=(?:.*[^a-zA-ZÅÄÖåäö0-9]){2,}).{8,}$",
            message = "Lösenordet måste vara minst 8 tecken långt och innehålla minst 1 stor bokstav, 2 siffror och 2 specialtecken"
    )
    private String password;

    /**
     * Måste vara true för att användaren godkänt GDPR.
     */
    @NotNull(message = "Du måste godkänna GDPR avtalet!")
    private boolean consentGiven;

    /**
     * Roll – måste vara 'USER' eller 'ADMIN'.
     */
    @NotBlank(message = "Rollen får inte vara tom.")
    @Pattern(regexp = "^(USER|ADMIN)$", message = "Rollen måste vara 'USER' eller 'ADMIN'.")
    private String role;

    // GETTERS & SETTERS

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isConsentGiven() {
        return consentGiven;
    }

    public void setConsentGiven(boolean consentGiven) {
        this.consentGiven = consentGiven;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
