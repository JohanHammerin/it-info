package se.johan.ituppgift;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registration")
public class CreateAccountController {

    private CreateAccountService createAccountService;

    public CreateAccountController(CreateAccountService createAccountService) {

        this.createAccountService = createAccountService;
    }

    @PostMapping("/new")
    public ResponseEntity<String> createUser(@Valid String username, @Valid String password, @Valid String role, @Valid boolean consentGiven) {
        createAccountService.createUser(username, password, role, consentGiven);
        return ResponseEntity.ok("Användare skapad");
    }
}
