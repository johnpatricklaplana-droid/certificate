package john.patrick.laplana.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import john.patrick.laplana.dto.GlobalResponse;
import john.patrick.laplana.dto.SchoolDto;
import john.patrick.laplana.service.SchoolService;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;


@RestController
@RequiredArgsConstructor
public class SchoolController {

    private final SchoolService schoolService;
    
    @PostMapping("/api/school")
    public ResponseEntity<GlobalResponse> postMethodName(
        @RequestPart("school") SchoolDto school,
        @RequestPart("schoolLogo") MultipartFile schoolLogo
    ) {
        schoolService.registerSchool(school, schoolLogo);
        
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(new GlobalResponse(201, "successful one", LocalDateTime.now(), true));
    }
    

}
