package se.johan.ituppgift;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Huvudklass för att starta Spring Boot-applikationen.
 * @SpringBootApplication aktiverar automatiska konfigurationer och komponent-scan.
 */
@SpringBootApplication
public class ItUppgiftApplication {

    /**
     * Applikationens startpunkt.
     * Metoden startar Spring Boot-applikationen med eventuella kommandoradsargument.
     *
     * @param args argument från kommandoraden (kan vara tomt)
     */
    public static void main(String[] args) {
        SpringApplication.run(ItUppgiftApplication.class, args);
    }

}
