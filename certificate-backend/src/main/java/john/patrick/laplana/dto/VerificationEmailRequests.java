package john.patrick.laplana.dto;

public record VerificationEmailRequests(
    String toEmail,
    String schoolName,
    String token
) {} 

