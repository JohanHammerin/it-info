package se.johan.ituppgift.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.johan.ituppgift.dto.AppUserDTO;
import se.johan.ituppgift.model.AppUser;
import se.johan.ituppgift.service.AppUserService;
import se.johan.ituppgift.service.CreateAccountService;

@RestController
@RequestMapping("/registration")
public class CreateAccountController {

    private CreateAccountService createAccountService;
    public CreateAccountController(CreateAccountService createAccountService, AppUserService appUserService) {
        this.createAccountService = createAccountService;
    }

    @PostMapping("/new")
    public ResponseEntity<AppUser> create(@RequestBody @Valid AppUserDTO dto) {
        AppUser savedUser = createAccountService.createUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }
}
