package se.johan.ituppgift.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import se.johan.ituppgift.dto.AppUserDTO;
import se.johan.ituppgift.repository.AppUserRepository;
import se.johan.ituppgift.model.AppUser;

@Service
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    // Constructor with both dependencies
    public AppUserService(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AppUserDTO toDTO(AppUser user) {
        AppUserDTO dto = new AppUserDTO();
        dto.setUsername(user.getUsername());
        return dto;
    }

    /**
     * @param appUser
     * @return
     */
    public AppUser toUser(AppUserDTO appUser) {
        AppUser user = new AppUser();
        user.setUsername(appUser.getUsername().trim());
        user.setPassword(passwordEncoder.encode(appUser.getPassword()));
        user.setConsentGiven(appUser.isConsentGiven());
        user.setRole(appUser.getRole());
        return user;
    }
}