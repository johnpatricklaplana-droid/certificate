package john.patrick.laplana.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import john.patrick.laplana.entities.School;

public interface SchoolRepository extends JpaRepository<School, UUID> {

    @Query("SELECT s FROM School s WHERE s.isVerified = false")
    List<School> getSchoolRegistrationRequests();

    Optional<School> findByVerificationToken(String token);
    
}
