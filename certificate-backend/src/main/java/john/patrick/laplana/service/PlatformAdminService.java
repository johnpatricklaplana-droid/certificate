package john.patrick.laplana.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import john.patrick.laplana.dto.EmailAndPasswordDto;
import john.patrick.laplana.dto.SchoolDto;
import john.patrick.laplana.entities.PlatformAdmin;
import john.patrick.laplana.mapper.SchoolMapper;
import john.patrick.laplana.repositories.PlatformAdminRepository;
import john.patrick.laplana.repositories.SchoolRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlatformAdminService {

    private final PlatformAdminRepository platformAdminRepo;
    private final PasswordEncoder passwordEncoder;
    private final SchoolRepository schoolRepo;
    private final SchoolMapper schoolMapper;
    
    public PlatformAdmin platformAdminLogin(EmailAndPasswordDto emailAndPassword) {

        String email = emailAndPassword.email();
        String password = emailAndPassword.password();

        PlatformAdmin admin = platformAdminRepo.findByEmail(email).orElse(null);

        if(admin == null) {
            // TODO: 
        }

        if(!passwordEncoder.matches(password, admin.getPassword())) {
            // TODO: 
        }

        return admin;

    }

    public List<SchoolDto> getSchoolRegistrationRequest() {
        
        return schoolRepo.getSchoolRegistrationRequests().stream()
            .map(schoolMapper::toSchoolDto)
            .toList();

    }

}
