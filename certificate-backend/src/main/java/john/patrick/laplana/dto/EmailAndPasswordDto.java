package john.patrick.laplana.dto;

import jakarta.validation.constraints.NotBlank;

public record EmailAndPasswordDto(
    
    @NotBlank(message = "email is required buddy")
    String email,

    @NotBlank(message = "password is required buddy")
    String password
) {}
