package john.patrick.laplana.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import john.patrick.laplana.entities.PlatformAdmin;

public interface PlatformAdminRepository extends JpaRepository<PlatformAdmin, UUID> {

    boolean existsByEmail(String string);

    Optional<PlatformAdmin> findByEmail(String email);
    
}
