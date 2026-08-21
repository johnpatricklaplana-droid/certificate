package john.patrick.laplana.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import john.patrick.laplana.dto.SchoolDto;
import john.patrick.laplana.entities.School;
import john.patrick.laplana.mapper.SchoolMapper;
import john.patrick.laplana.repositories.SchoolRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SchoolService {
    
    private final SupabaseUploadService supabaseUploadService;
    private final SchoolMapper schoolMapper;
    private final SchoolRepository schoolRepo;

    public void registerSchool(SchoolDto schoolDto,MultipartFile multipartFile) {
        
        try {
            String schoolLogoUrl = supabaseUploadService.uploadFile("school_logo", multipartFile);

            School school = schoolMapper.toSchool(schoolDto);
            school.setLogoUrl(schoolLogoUrl);
            school.setVerified(false);
            school.setVerificationToken(UUID.randomUUID());
            school.setTokenExpiresAt(LocalDateTime.now().plusDays(7));

            schoolRepo.save(school);

        } catch (Exception e) {
           // TODO
        }

    }

    public void verifySchool(String token, String response) {
        
        School school = schoolRepo.findByVerificationToken(token).orElse(null);

        if(school == null) {
            // TODO: 
        }

        if(LocalDateTime.now().isAfter(school.getTokenExpiresAt())) {
            // TODO: reject some 
        }

        if(response.equals("yes")) {
            school.setVerified(true);
        }

        if(response.equals("no")) {
            school.setVerified(false);
        }


    }

}
