package se.johan.ituppgift.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import se.johan.ituppgift.LoggingComponent;
import se.johan.ituppgift.dto.AppUserDTO;
import se.johan.ituppgift.model.AppUser;

/**
 * Service för hantering av AppUser-logik.
 */
@Service
public class AppUserService {

    private final PasswordEncoder passwordEncoder;
    private final LoggingComponent loggingComponent;

    /**
     * Konstruktor.
     *
     * @param passwordEncoder Krypterar lösenord.
     * @param loggingComponent Loggning.
     */
    public AppUserService(PasswordEncoder passwordEncoder, LoggingComponent loggingComponent) {
        this.passwordEncoder = passwordEncoder;
        this.loggingComponent = loggingComponent;
    }

    /**
     * Konverterar AppUserDTO till AppUser med krypterat lösenord.
     *
     * @param appUser DTO med användardata.
     * @return AppUser-entity.
     */
    public AppUser toUser(AppUserDTO appUser) {
        loggingComponent.logInfo("Skapar en användare");
        AppUser user = new AppUser();
        user.setUsername(appUser.getUsername().trim());
        user.setPassword(passwordEncoder.encode(appUser.getPassword()));
        user.setConsentGiven(appUser.isConsentGiven());
        user.setRole(appUser.getRole());
        loggingComponent.logInfo("Användare skapad");
        return user;
    }
}
