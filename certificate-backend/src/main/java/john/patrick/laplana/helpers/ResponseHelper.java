package john.patrick.laplana.helpers;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import john.patrick.laplana.dto.GlobalResponse;

public class ResponseHelper {
    
    public static ResponseEntity<GlobalResponse> okResponse(String responseBody) {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(new GlobalResponse(200, responseBody, LocalDateTime.now(), true));
    }

    public static ResponseEntity<GlobalResponse> createdResponse(String responseBody) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new GlobalResponse(201, responseBody, LocalDateTime.now(), true));
    }

}
