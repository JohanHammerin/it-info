package se.johan.ituppgift.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.johan.ituppgift.dto.AppUserDTO;
import se.johan.ituppgift.model.AppUser;
import se.johan.ituppgift.service.AppUserService;
import se.johan.ituppgift.service.CreateAccountService;

/**
 * Controller som hanterar registrering av nya användare.
 *
 * Här kan man skapa ett konto via ett POST-anrop.
 */
@RestController
@RequestMapping("/registration")
public class CreateAccountController {

    private final CreateAccountService createAccountService;

    /**
     * Konstruktor som tar in servicen som sköter själva skapandet av användare.
     *
     * @param createAccountService Service för att skapa nya konton
     * @param appUserService Finns med här men används inte just nu
     */
    public CreateAccountController(CreateAccountService createAccountService, AppUserService appUserService) {
        this.createAccountService = createAccountService;
    }

    /**
     * POST-endpoint som försöker skapa en ny användare.
     *
     * DTO:n innehåller info som namn, användarnamn, lösenord m.m.
     * Validering sker automatiskt tack vare @Valid.
     *
     * @param dto Användarens info
     * @return 201 Created om allt gick bra, annars 400 Bad Request
     */
    @PostMapping("/new")
    public ResponseEntity<String> create(@Valid AppUserDTO dto) {
        createAccountService.createUser(dto);
        if (dto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Användare skapades ej");
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body("Användare skapad");
        }
    }
}
