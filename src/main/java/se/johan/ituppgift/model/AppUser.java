package se.johan.ituppgift.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Representerar en användare.
 */
@Entity
public class AppUser {

    /**
     * Unikt ID, genereras automatiskt.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Användarnamn.
     */
    private String username;

    /**
     * Krypterat lösenord.
     */
    private String password;

    /**
     * Användarroll, t.ex. "USER" eller "ADMIN".
     */
    private String role;

    /**
     * Om användaren har gett sitt samtycke.
     */
    private boolean consentGiven;

    // Getters och setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isConsentGiven() {
        return consentGiven;
    }

    public void setConsentGiven(boolean consentGiven) {
        this.consentGiven = consentGiven;
    }
}
