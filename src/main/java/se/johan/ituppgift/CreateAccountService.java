package se.johan.ituppgift;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class CreateAccountService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(CreateAccountService.class);

    public CreateAccountService(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/new")
    public AppUser createUser(String username, String password, String role, boolean consentGiven) {
        logger.info("Creating new user");
        AppUser appUser = null;
        try {
            logger.debug("Starting task");
            appUser = new AppUser();
            appUser.setUsername(username);
            appUser.setPassword(passwordEncoder.encode(password));
            appUser.setRole(role);
            appUser.setConsentGiven(consentGiven);
            appUserRepository.save(appUser);
        } catch (Exception e) {
            logger.error("Error creating new user", e);
        }
        return appUser;
    }
}
