package john.patrick.laplana.controller;

import org.springframework.web.bind.annotation.RestController;

import john.patrick.laplana.dto.EmailAndPasswordDto;
import john.patrick.laplana.dto.GlobalResponse;
import john.patrick.laplana.dto.VerificationEmailRequests;
import john.patrick.laplana.entities.PlatformAdmin;
import john.patrick.laplana.service.EmailService;
import john.patrick.laplana.service.JwtService;
import john.patrick.laplana.service.PlatformAdminService;
import lombok.RequiredArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequiredArgsConstructor
public class PlaformAdminController {

    private final PlatformAdminService platformAdminService;
    private final JwtService jwtService;
    private final EmailService emailService;
    
    @PostMapping("/api/auth/admin")
    public ResponseEntity<GlobalResponse> postMethodName(
        @RequestBody EmailAndPasswordDto emailAndPassword
    ) {
        PlatformAdmin admin = platformAdminService.platformAdminLogin(emailAndPassword);

        String token = jwtService.generateToken(admin.getId().toString(), admin.getEmail(), admin.getRole());

        ResponseCookie cookie = ResponseCookie.from("jwt-token", token)
            .httpOnly(true)
            .secure(true)
            .sameSite("Strict")
            .path("/")
            .maxAge(Duration.ofDays(10))
            .build();

        return ResponseEntity
            .status(HttpStatus.OK)
            .header(HttpHeaders.SET_COOKIE, cookie.toString())
            .body(new GlobalResponse(200, "TODO", LocalDateTime.now(), true));
    } 

    @GetMapping("/api/platform-admin/school")
    public ResponseEntity<GlobalResponse> getMethodName() {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(new GlobalResponse(200, platformAdminService.getSchoolRegistrationRequest(), LocalDateTime.now(), true));
    }
    
    @PostMapping("/api/platform-admin/email/school")
    public ResponseEntity<GlobalResponse> sendEmailVerification(@RequestBody VerificationEmailRequests entity) {
        emailService.sendVerificationEmail(entity);
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(new GlobalResponse(200, "todo", LocalDateTime.now(), true));
    }
    

}
