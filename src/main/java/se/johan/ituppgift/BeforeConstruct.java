package se.johan.ituppgift;

import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BeforeConstruct {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    BeforeConstruct(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        if (appUserRepository.findByUsername("user") == null) {
            AppUser user = new AppUser();
            user.setUsername("user");

            user.setPassword(passwordEncoder.encode("password"));
            user.setRole("ADMIN");
            user.setConsentGiven(true);
            appUserRepository.save(user);
        }
    }
}
