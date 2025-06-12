package se.johan.ituppgift.model;

/**
 * Inloggningsförfrågan med användarnamn och lösenord.
 *
 * @param username Användarnamn
 * @param password Lösenord
 */
public record LoginRequest(String username, String password) {
}
