package se.johan.ituppgift.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

//SHOW USERS

public class ShowAppUserDTO {

    private Long id;

    @NotBlank(message = "Användarnamnet får inte vara tomt")
    private String username;

    @NotBlank(message = "Rollen får inte vara tom")
    private String role;

    @NotNull(message = "Du måste godkänna GDPR avtalet!")
    private boolean consentGiven;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Användarnamnet får inte vara tomt") String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank(message = "Användarnamnet får inte vara tomt") String username) {
        this.username = username;
    }

    public @NotBlank(message = "Rollen får inte vara tom") String getRole() {
        return role;
    }

    public void setRole(@NotBlank(message = "Rollen får inte vara tom") String role) {
        this.role = role;
    }

    @NotNull(message = "Du måste godkänna GDPR avtalet!")
    public boolean isConsentGiven() {
        return consentGiven;
    }

    public void setConsentGiven(@NotNull(message = "Du måste godkänna GDPR avtalet!") boolean consentGiven) {
        this.consentGiven = consentGiven;
    }
}
