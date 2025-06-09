package se.johan.ituppgift.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import se.johan.ituppgift.dto.AppUserDTO;
import se.johan.ituppgift.model.AppUser;
import se.johan.ituppgift.repository.AppUserRepository;

// Service
@Service
public class CreateAccountService {

    private final AppUserRepository appUserRepository;
    private final AppUserService appUserService;


    public CreateAccountService(AppUserRepository appUserRepository, AppUserService appUserService) {
        this.appUserRepository = appUserRepository;
        this.appUserService = appUserService;
    }

    public AppUser createUser(AppUserDTO dto) {
        AppUser appUser = appUserService.toUser(dto);
        return appUserRepository.save(appUser);
    }
}
