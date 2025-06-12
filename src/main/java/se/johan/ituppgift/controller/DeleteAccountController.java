package se.johan.ituppgift.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.johan.ituppgift.dto.AppUserDTO;
import se.johan.ituppgift.model.AppUser;
import se.johan.ituppgift.service.AppUserService;
import se.johan.ituppgift.service.DeleteAccountService;

/**
 * Controller för att ta bort användare.
 *
 * Här finns en endpoint som raderar en användare baserat på ID.
 */
@RestController
@RequestMapping("/delete")
public class DeleteAccountController {

    private final DeleteAccountService deleteAccountService;
    private final AppUserService appUserService;

    /**
     * Konstruktor som tar in services för borttagning och hantering av användare.
     *
     * @param deleteAccountService Sköter själva raderingen
     * @param appUserService Hanterar användarlogik (används inte direkt här)
     */
    public DeleteAccountController(DeleteAccountService deleteAccountService, AppUserService appUserService) {
        this.deleteAccountService = deleteAccountService;
        this.appUserService = appUserService;
    }

    /**
     * DELETE-endpoint som raderar en användare via ID.
     *
     * @param id ID för användaren som ska tas bort
     * @return 200 OK med ett enkelt meddelande om att det lyckades
     */
    @DeleteMapping("/user")
    public ResponseEntity<String> deleteUser(@RequestParam long id) {
        deleteAccountService.deleteAccount(id);
        return ResponseEntity.ok("Användare med id:" + id + " har raderats");
    }
}
