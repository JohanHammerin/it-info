package se.johan.ituppgift.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.johan.ituppgift.dto.AppUserDTO;
import se.johan.ituppgift.model.AppUser;
import se.johan.ituppgift.service.AppUserService;
import se.johan.ituppgift.service.DeleteAccountService;
@RestController
@RequestMapping("/delete")

public class DeleteAccountController {
    private final DeleteAccountService deleteAccountService;
    private final AppUserService appUserService;

    public DeleteAccountController(DeleteAccountService deleteAccountService, AppUserService appUserService) {
        this.deleteAccountService = deleteAccountService;
        this.appUserService = appUserService;
    }
    @DeleteMapping("/user")
    public ResponseEntity<String> deleteUser(long id) {
        deleteAccountService.deleteAccount(id);
        return ResponseEntity.ok("Användare raderad");
    }
}
