package se.johan.ituppgift;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/info")
public class InfoController {

    private final AppUserRepository appUserRepository;

    public InfoController(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @GetMapping("/totalUsers")
    public String getTotalUser() {
        long totalUsers = appUserRepository.count();
        return "Antal användare: " + totalUsers;
    }
}
