package se.johan.ituppgift.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import se.johan.ituppgift.LoggingComponent;
import se.johan.ituppgift.dto.AppUserDTO;
import se.johan.ituppgift.repository.AppUserRepository;
import se.johan.ituppgift.model.AppUser;

@Service
public class AppUserService {

    private final PasswordEncoder passwordEncoder;

    private LoggingComponent loggingComponent;

    public AppUserService(PasswordEncoder passwordEncoder, LoggingComponent loggingComponent) {
        this.passwordEncoder = passwordEncoder;
        this.loggingComponent = loggingComponent;
    }

    //    public AppUserDTO toDTO(AppUser user) {
//        AppUserDTO dto = new AppUserDTO();
//        dto.setUsername(user.getUsername());
//        return dto;
//    }

    /**
     * @param appUser
     * @return
     */
    public AppUser toUser(AppUserDTO appUser) {
        AppUser user = new AppUser();
        loggingComponent.logInfo("Skapar en användare");
        user.setUsername(appUser.getUsername().trim());
        user.setPassword(passwordEncoder.encode(appUser.getPassword()));
        user.setConsentGiven(appUser.isConsentGiven());
        user.setRole(appUser.getRole());
        loggingComponent.logInfo("Användare skapad");
        return user;
    }
}