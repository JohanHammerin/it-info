package se.johan.ituppgift;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Komponent för centraliserad loggning med olika loggnivåer.
 * Underlättar enhetlig och enkel loggning i applikationen.
 */
@Component
public class LoggingComponent {

    private static final Logger logger = LoggerFactory.getLogger(LoggingComponent.class);

    /**
     * Loggar ett informationsmeddelande (INFO-nivå).
     *
     * @param message Texten som ska loggas som information.
     */
    public void logInfo(String message) {
        logger.info(message);
    }

    /**
     * Loggar ett felmeddelande (ERROR-nivå).
     *
     * @param message Texten som ska loggas som fel.
     */
    public void logError(String message) {
        logger.error(message);
    }

    /**
     * Loggar ett varningsmeddelande (WARN-nivå).
     *
     * @param message Texten som ska loggas som varning.
     */
    public void logWarning(String message) {
        logger.warn(message);
    }

    /**
     * Loggar ett debugmeddelande (DEBUG-nivå).
     *
     * @param message Texten som ska loggas för debugging.
     */
    public void logDebug(String message) {
        logger.debug(message);
    }
}
