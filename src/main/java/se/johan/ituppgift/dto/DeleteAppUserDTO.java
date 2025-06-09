package se.johan.ituppgift.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class DeleteAppUserDTO {

   @NotNull (message = "Id får inte vara tomt.")
   @Positive (message = "Id får inte vara mindre än 0.")
   private Long id;

    public @NotNull(message = "Id får inte vara tomt.") @Positive(message = "Id får inte vara mindre än 0.") Long getId() {
        return id;
    }

    public void setId(@NotNull(message = "Id får inte vara tomt.") @Positive(message = "Id får inte vara mindre än 0.") Long id) {
        this.id = id;
    }
}
