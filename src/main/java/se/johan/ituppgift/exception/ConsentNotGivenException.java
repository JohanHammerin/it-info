package se.johan.ituppgift.exception;

public class ConsentNotGivenException extends RuntimeException {
    public ConsentNotGivenException(String message) {
        super(message);
    }
}
