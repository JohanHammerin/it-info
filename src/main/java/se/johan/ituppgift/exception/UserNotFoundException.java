package se.johan.ituppgift.exception;

/**
 * Kastas när en användare inte hittas.
 */
public class UserNotFoundException extends RuntimeException {

    /**
     * Skapar exception med ett felmeddelande.
     *
     * @param message Beskrivning av felet
     */
    public UserNotFoundException(String message) {
        super(message);
    }
}
