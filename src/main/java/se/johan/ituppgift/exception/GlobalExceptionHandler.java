package se.johan.ituppgift.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Hanterar globala undantag och skickar anpassade HTTP-svar.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Returnerar 404 om användaren inte hittas.
     *
     * @param ex Undantaget som kastats
     * @return HTTP 404 med felmeddelande
     */
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFound(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    /**
     * Returnerar 409 om användaren inte har godkänt GDPR.
     *
     * @param ex Undantaget som kastats
     * @return HTTP 409 med felmeddelande
     */
    @ExceptionHandler(ConsentNotGivenException.class)
    public ResponseEntity<String> handleConsentNotGiven(ConsentNotGivenException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
}
