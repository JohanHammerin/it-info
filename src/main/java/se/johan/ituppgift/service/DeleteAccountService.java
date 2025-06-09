package se.johan.ituppgift.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import se.johan.ituppgift.model.AppUser;
import se.johan.ituppgift.repository.AppUserRepository;

@Service
public class DeleteAccountService {

    private final AppUserRepository appUserRepository;

    public DeleteAccountService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @DeleteMapping("/delete")
    public String deleteAccount(long id) {
        AppUser appUser = new AppUser();
        appUser.setId(id);
        appUserRepository.delete(appUser);
        return appUser.toString();
    }
}
