package se.johan.ituppgift.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class AppUserDTO {

    @NotBlank(message = "Användarnamnet får inte vara tomt")
    private String username;

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


    @NotNull(message = "Du måste godkänna GDPR avtalet!")
    private boolean consentGiven;

    @NotBlank(message = "Rollen får inte vara tom.")
    private String role;

    public @NotBlank(message = "Användarnamnet får inte vara tomt") String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank(message = "Användarnamnet får inte vara tomt") String username) {
        this.username = username;
    }

    public @NotBlank(message = "Lösenordet får inte vara tomt") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Lösenordet får inte vara tomt") String password) {
        this.password = password;
    }

    @NotNull(message = "Du måste godkänna GDPR avtalet!")
    public boolean isConsentGiven() {
        return consentGiven;
    }

    public void setConsentGiven(@NotNull(message = "Du måste godkänna GDPR avtalet!") boolean consentGiven) {
        this.consentGiven = consentGiven;
    }

    public @NotBlank(message = "Rollen får inte vara tom.") String getRole() {
        return role;
    }

    public void setRole(@NotBlank(message = "Rollen får inte vara tom.") String role) {
        this.role = role;
    }
}
