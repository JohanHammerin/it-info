package se.johan.ituppgift.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.johan.ituppgift.dto.AppUserDTO;
import se.johan.ituppgift.repository.AppUserRepository;

import java.nio.file.AccessDeniedException;
import java.util.Objects;

@RestController
public class InfoController {

    private final AppUserRepository appUserRepository;

    public InfoController(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    // Hjälpmetod: Kolla om användaren har en viss roll
    private boolean hasRole(Authentication auth, String role) {
        return auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals(role));
    }

    @GetMapping("/totalUsers")
    public String getTotalUser() {
        long totalUsers = appUserRepository.count();
        return "Antal användare: " + totalUsers;
    }

    @GetMapping("/user")
    public ResponseEntity<String> user() {
            return ResponseEntity.ok("Välkommen användare USER/ADMIN");
    }

    @GetMapping("/admin")
    public ResponseEntity<String> admin() {
        return ResponseEntity.ok("Välkommen ADMIN");
    }

    @GetMapping("/public")
    public ResponseEntity<String> welcome() {
        return ResponseEntity.ok("Välkommen PUBLIC");
    }
}
