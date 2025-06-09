package se.johan.ituppgift.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.johan.ituppgift.dto.CreateAppUserDTO;
import se.johan.ituppgift.service.CreateAccountService;

@RestController
@RequestMapping("/registration")
public class CreateAccountController {

    private CreateAccountService createAccountService;

    public CreateAccountController(CreateAccountService createAccountService) {

        this.createAccountService = createAccountService;
    }

    @PostMapping("/new")
    public ResponseEntity<String> createUser(@Valid CreateAppUserDTO dto) {
        createAccountService.createUser(dto.getUsername(), dto.getPassword(), dto.getRole(), dto.isConsentGiven());
        return ResponseEntity.ok("Användare skapad");
    }
}
