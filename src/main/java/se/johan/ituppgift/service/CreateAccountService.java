package se.johan.ituppgift.service;

import org.springframework.stereotype.Service;
import se.johan.ituppgift.LoggingComponent;
import se.johan.ituppgift.dto.AppUserDTO;
import se.johan.ituppgift.exception.ConsentNotGivenException;
import se.johan.ituppgift.model.AppUser;
import se.johan.ituppgift.repository.AppUserRepository;

/**
 * Service för att skapa användarkonton.
 */
@Service
public class CreateAccountService {

    private final AppUserRepository appUserRepository;
    private final AppUserService appUserService;
    private final LoggingComponent loggingComponent;

    /**
     * Konstruktor.
     *
     * @param appUserRepository Repository för användare.
     * @param appUserService    Hantering av användarlogik.
     * @param loggingComponent  Loggning.
     */
    public CreateAccountService(AppUserRepository appUserRepository, AppUserService appUserService, LoggingComponent loggingComponent) {
        this.appUserRepository = appUserRepository;
        this.appUserService = appUserService;
        this.loggingComponent = loggingComponent;
    }

    /**
     * Skapar och sparar en användare om samtycke är givet.
     *
     * @param dto Användardata.
     * @return Sparad AppUser.
     * @throws ConsentNotGivenException Om samtycke saknas.
     */
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
