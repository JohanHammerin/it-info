package se.johan.ituppgift;

import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import se.johan.ituppgift.model.AppUser;
import se.johan.ituppgift.repository.AppUserRepository;

/**
 * Komponent som initierar och skapar en standardanvändare i databasen vid applikationens start.
 */
@Component
public class BeforeConstruct {

    private final AppUserRepository appUserRepository; // Repository för användare
    private final PasswordEncoder passwordEncoder;     // Krypterar lösenord

    /**
     * Konstruktor för BeforeConstruct.
     *
     * @param appUserRepository Repository för användare.
     * @param passwordEncoder   PasswordEncoder för att kryptera lösenord.
     */
    public BeforeConstruct(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Initierar standardanvändaren "user" med lösenord "password" och rollen ADMIN,
     * om användaren inte redan finns i databasen.
     * Denna metod körs automatiskt direkt efter att komponenten har skapats.
     */
    @PostConstruct
    public void init() {
        // Kontrollera om användaren "user" redan finns
        if (appUserRepository.findByUsername("user") == null) {
            AppUser user = new AppUser();
            user.setUsername("user");
            // Kryptera lösenord innan sparande
            user.setPassword(passwordEncoder.encode("password"));
            user.setRole("ADMIN");
            user.setConsentGiven(true); // Sätter att användaren gett sitt samtycke
            appUserRepository.save(user); // Spara användaren i databasen
        }
    }
}
