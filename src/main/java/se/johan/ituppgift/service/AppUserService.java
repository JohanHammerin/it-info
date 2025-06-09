package se.johan.ituppgift.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.dao.DataIntegrityViolationException;
import se.johan.ituppgift.repository.AppUserRepository;
import se.johan.ituppgift.dto.CreateAppUserDTO;
import se.johan.ituppgift.dto.ShowAppUserDTO;
import se.johan.ituppgift.exception.UserNotFoundException;
import se.johan.ituppgift.model.AppUser;

@Service
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    // Constructor with both dependencies
    public AppUserService(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AppUser create(CreateAppUserDTO dto) {
        AppUser user = toUser(dto);

        try {
            // Save to repository
            return appUserRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new UserNotFoundException("Username already exists" + e);
        }
    }

    public ShowAppUserDTO toDTO(AppUser user) {
        if (user == null) {
            return null;
        }

        ShowAppUserDTO dto = new ShowAppUserDTO();
        dto.setUsername(user.getUsername());
        return dto;
    }

    public AppUser toUser(CreateAppUserDTO dto) {
        if (dto == null) {
            return null;
        }

        AppUser user = new AppUser();
        user.setUsername(dto.getUsername().trim());
        // Encode password before storing
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        return user;
    }
}