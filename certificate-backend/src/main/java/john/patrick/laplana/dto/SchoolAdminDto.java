package john.patrick.laplana.dto;

import jakarta.validation.constraints.NotBlank;

public record SchoolAdminDto(
    @NotBlank(message = "email is required buddy")
    String email,

    @NotBlank(message = "name is required buddy")
    String fullName
    
) {}
