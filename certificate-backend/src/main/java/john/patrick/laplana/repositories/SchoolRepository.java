package john.patrick.laplana.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import john.patrick.laplana.entities.School;

public interface SchoolRepository extends JpaRepository<School, UUID> {
    
}
