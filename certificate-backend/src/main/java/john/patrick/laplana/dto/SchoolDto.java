package john.patrick.laplana.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;

public record SchoolDto(
    UUID id,

    @NotBlank(message = "name is required buddy")
    String name,
    
    String domain,
    String logoUrl,

    @NotBlank(message = "email is required buddy")
    String email,

    @NotBlank(message = "address is required buddy")
    String address,

    String verificationToken,
    
    String website
) {}
