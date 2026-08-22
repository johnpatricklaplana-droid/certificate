package john.patrick.laplana.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;
import john.patrick.laplana.dto.SchoolDto;
import john.patrick.laplana.dto.SchoolWithAdminDto;
import john.patrick.laplana.entities.School;
import john.patrick.laplana.entities.SchoolAdmin;
import john.patrick.laplana.mapper.SchoolMapper;
import john.patrick.laplana.repositories.SchoolRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SchoolService {
    
    private final SupabaseUploadService supabaseUploadService;
    private final SchoolMapper schoolMapper;
    private final SchoolRepository schoolRepo;

    @Transactional
    public void registerSchool(SchoolWithAdminDto sWAD, MultipartFile multipartFile) {
        
        try {
            String schoolLogoUrl = supabaseUploadService.uploadFile("school_logo", multipartFile);

            School school = schoolMapper.toSchool(sWAD.school());
            school.setLogoUrl(schoolLogoUrl);
            school.setVerified(false);
            school.setVerificationToken(UUID.randomUUID());
            school.setTokenExpiresAt(LocalDateTime.now().plusDays(7));

            List<SchoolAdmin> admins = sWAD.schoolAdmin().stream()
                .map(sA -> {
                        SchoolAdmin schoolAdmin = new SchoolAdmin();
                        schoolAdmin.setEmail(sA.email());
                        schoolAdmin.setFullName(sA.fullName());
                        schoolAdmin.setSchool(school);

                        return schoolAdmin;
                })
                .toList();

            school.setSchoolAdmins(admins);

            schoolRepo.save(school);

        } catch (Exception e) {
           // TODO
        }

    }

    public void verifySchool(UUID token, String response) {
        
        School school = schoolRepo.findByVerificationToken(token).orElse(null);

        if(school == null) {
            // TODO: 
        }

        if(LocalDateTime.now().isAfter(school.getTokenExpiresAt())) {
            // TODO: reject some 
        }

        if(response.equalsIgnoreCase("yes")) {
            school.setVerified(true);
            school.setVerificationToken(null);
            school.setVerifiedAt(LocalDateTime.now());

            schoolRepo.save(school);
            return;
        }

        if(response.equalsIgnoreCase("no")) {
            school.setVerified(false);
            school.setVerificationToken(null);
            
            schoolRepo.save(school);
        }

    }

}
