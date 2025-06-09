package se.johan.ituppgift.service;

import org.hibernate.dialect.function.LpadRpadPadEmulation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import se.johan.ituppgift.model.AppUser;
import se.johan.ituppgift.repository.AppUserRepository;

@Service

public class DeleteAccountService {

    private static final Logger logger = LoggerFactory.getLogger(DeleteAccountService.class);
    private final AppUserRepository appUserRepository;

    public DeleteAccountService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public void deleteAccount(long id) {
        AppUser appUser = new AppUser();
        appUser.setId(id);
        appUserRepository.delete(appUser);
    }
}
