package se.johan.ituppgift.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import se.johan.ituppgift.LoggingComponent;
import se.johan.ituppgift.dto.AppUserDTO;
import se.johan.ituppgift.exception.ConsentNotGivenException;
import se.johan.ituppgift.model.AppUser;
import se.johan.ituppgift.repository.AppUserRepository;

// Service
@Service
public class CreateAccountService {

    private final AppUserRepository appUserRepository;
    private final AppUserService appUserService;
    private final LoggingComponent loggingComponent;

    public CreateAccountService(AppUserRepository appUserRepository, AppUserService appUserService, LoggingComponent loggingComponent) {
        this.appUserRepository = appUserRepository;
        this.appUserService = appUserService;
        this.loggingComponent = loggingComponent;
    }

    public AppUser createUser(AppUserDTO dto) {
        AppUser appUser = appUserService.toUser(dto);
        if (!appUser.isConsentGiven()) {
            loggingComponent.logError("Consent is not given");
            throw new ConsentNotGivenException("Du måste godkänna GDPR");
        }
        loggingComponent.logInfo("User saved to database");
        return appUserRepository.save(appUser);
    }
}
