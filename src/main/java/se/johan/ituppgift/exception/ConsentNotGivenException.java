package se.johan.ituppgift.exception;

/**
 * Kastas om användaren inte har godkänt samtycke (GDPR).
 */
public class ConsentNotGivenException extends RuntimeException {

    /**
     * Skapar exception med ett eget felmeddelande.
     *
     * @param message Beskrivning av felet
     */
    public ConsentNotGivenException(String message) {
        super(message);
    }
}
