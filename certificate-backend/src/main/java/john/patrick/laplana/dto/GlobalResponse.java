package john.patrick.laplana.dto;

import java.time.LocalDateTime;

public record GlobalResponse(
    int status_code,
    Object response_body,
    LocalDateTime date,
    boolean success 
) {}
