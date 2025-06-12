package se.johan.ituppgift.service;

import org.springframework.stereotype.Service;
import se.johan.ituppgift.LoggingComponent;
import se.johan.ituppgift.model.AppUser;
import se.johan.ituppgift.repository.AppUserRepository;

/**
 * Service för att ta bort användarkonton.
 */
@Service
public class DeleteAccountService {

    private final AppUserRepository appUserRepository;
    private final LoggingComponent loggingComponent;

    /**
     * Konstruktor.
     *
     * @param appUserRepository repository för användare
     * @param loggingComponent  loggningskomponent
     */
    public DeleteAccountService(AppUserRepository appUserRepository, LoggingComponent loggingComponent) {
        this.appUserRepository = appUserRepository;
        this.loggingComponent = loggingComponent;
    }

    /**
     * Tar bort användare med angivet ID.
     *
     * @param id användarens ID
     */
    public void deleteAccount(long id) {
        AppUser appUser = new AppUser();
        loggingComponent.logInfo("Deleting account with id { " + id + " }");
        appUser.setId(id);
        appUserRepository.delete(appUser);
        loggingComponent.logInfo("Account Deleted");
    }
}
