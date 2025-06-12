package se.johan.ituppgift.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import se.johan.ituppgift.repository.AppUserRepository;

/**
 * Controller för att visa info om användare och olika roller.
 *
 * Innehåller endpoints för att kolla antal användare
 * och skicka olika svar beroende på roll.
 */
@RestController
public class InfoController {

    private final AppUserRepository appUserRepository;

    /**
     * Tar in vårt användar-repository via konstruktor.
     *
     * @param appUserRepository Repository för att hämta användardata
     */
    public InfoController(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    /**
     * Returnerar hur många användare som finns i databasen.
     *
     * @return text med totalt antal användare
     */
    @GetMapping("/totalUsers")
    public String getTotalUser() {
        long totalUsers = appUserRepository.count();
        return "Antal användare: " + totalUsers;
    }

    /**
     * Exempel på endpoint för inloggade användare med USER eller ADMIN-roll.
     *
     * @return ett enkelt välkomstmeddelande
     */
    @GetMapping("/user")
    public ResponseEntity<String> user() {
        return ResponseEntity.ok("Välkommen användare USER/ADMIN");
    }

    /**
     * Endpoint för användare med ADMIN-roll.
     *
     * @return ett enkelt meddelande till admin
     */
    @GetMapping("/admin")
    public ResponseEntity<String> admin() {
        return ResponseEntity.ok("Välkommen ADMIN");
    }

    /**
     * Publik endpoint – kräver ingen inloggning.
     *
     * @return meddelande till vem som helst
     */
    @GetMapping("/public")
    public ResponseEntity<String> welcome() {
        return ResponseEntity.ok("Välkommen PUBLIC");
    }
}
