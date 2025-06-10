package se.johan.ituppgift.service;

import org.hibernate.dialect.function.LpadRpadPadEmulation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import se.johan.ituppgift.LoggingComponent;
import se.johan.ituppgift.model.AppUser;
import se.johan.ituppgift.repository.AppUserRepository;

@Service

public class DeleteAccountService {

    private final AppUserRepository appUserRepository;
    private LoggingComponent loggingComponent;

    public DeleteAccountService(AppUserRepository appUserRepository, LoggingComponent loggingComponent) {
        this.appUserRepository = appUserRepository;
        this.loggingComponent = loggingComponent;
    }

    public void deleteAccount(long id) {
        AppUser appUser = new AppUser();
        loggingComponent.logInfo("Deleting account with id { " + id + " }");
        appUser.setId(id);
        loggingComponent.logInfo("Accounted Deleted");
        appUserRepository.delete(appUser);
    }
}
