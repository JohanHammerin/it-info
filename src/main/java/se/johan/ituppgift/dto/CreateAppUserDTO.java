package se.johan.ituppgift.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateAppUserDTO {

    @NotBlank(message = "Användarnamnet får inte vara tomt")
    private String username;

    @NotBlank (message = "Lösenordet får inte vara tomt")
    private String password;

    @NotNull(message = "Du måste godkänna GDPR avtalet!")
    private boolean consentGiven;

    @NotBlank (message = "Rollen får inte vara tom.")
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
