package se.johan.ituppgift;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LoggingComponent {

    private static final Logger logger = LoggerFactory.getLogger(LoggingComponent.class);

    public void logInfo(String message) {
        logger.info(message);
    }

    public void logError(String message) {
        logger.error(message);
    }

    public void logWarning(String message) {
        logger.warn(message);
    }

    public void logDebug(String message) {
        logger.debug(message);
    }
}