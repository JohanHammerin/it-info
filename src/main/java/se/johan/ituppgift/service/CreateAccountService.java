package se.johan.ituppgift.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import se.johan.ituppgift.repository.AppUserRepository;
import se.johan.ituppgift.model.AppUser;

@Service
public class CreateAccountService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public CreateAccountService(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/new")
    public AppUser createUser(String username, String password, String role, boolean consentGiven) {
        AppUser appUser = new AppUser();
        appUser.setUsername(username);
        appUser.setPassword(passwordEncoder.encode(password));
        appUser.setRole(role);
        appUser.setConsentGiven(consentGiven);
        appUserRepository.save(appUser);

        return appUser;
    }
}
