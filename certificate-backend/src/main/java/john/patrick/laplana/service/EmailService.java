package john.patrick.laplana.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import john.patrick.laplana.dto.VerificationEmailRequests;

@Service
public class EmailService {
    
    private final RestClient restClient = RestClient.create();

    @Value("${resend.api.key}")
    private String resendApiKey;

    public void sendVerificationEmail(VerificationEmailRequests vER) {
        String yesLink = "http://localhost:8080/api/schools/verify?token=" + vER.token() + "&response=yes";
        String noLink = "http://localhost:8080/api/schools/verify?token=" + vER.token() + "&response=no";

        String htmlBody = """
            <p>Hi,</p>
            <p>We received a registration request on CertiTrust claiming to represent <b>%s</b>.</p>
            <p style="margin-bottom: 16px;">Please confirm this is you:</p>
            <div>
                <a href="%s" style="padding:10px 18px;background:#111;color:#fff;text-decoration:none;border-radius:6px;margin-right:8px;">Yes, this is us</a>
                <a href="%s" style="padding:10px 18px;border:1px solid #ccc;color:#111;text-decoration:none;border-radius:6px;">No, not us</a>
            </div>
            <p>This link expires in 7 days.</p>
            """.formatted(vER.schoolName(), yesLink, noLink);

        Map<String, Object> body = new HashMap<>();
        body.put("from", "CertiTrust <onboarding@resend.dev>");
        body.put("to", List.of(vER.toEmail()));
        body.put("subject", "Confirm your institution on CertiTrust");
        body.put("html", htmlBody);

        restClient.post()
            .uri("https://api.resend.com/emails")
            .header("Authorization", "Bearer " + resendApiKey)
            .header("Content-Type", "application/json")
            .body(body)
            .retrieve()
            .toBodilessEntity();
    }


}
