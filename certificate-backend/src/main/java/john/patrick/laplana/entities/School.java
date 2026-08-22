package john.patrick.laplana.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name="schools")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class School extends BaseEntity {
    
    @Column(name = "name", length = 200, nullable = false)
    private String name;

    @Column(name = "domain", nullable = true)
    private String domain;

    @Column(name = "website", nullable = true)
    private String website;

    @Column(name = "school_address", nullable = false)
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name = "logo_url", nullable = false)
    private String logoUrl;

    @Column(name = "verification_token")
    private UUID verificationToken;

    @Column(name = "verification_token_expires_at")
    private LocalDateTime tokenExpiresAt;

    @Column(name = "is_verified")
    private boolean isVerified;

    @Column(name = "verified_at")
    private LocalDateTime verifiedAt;

    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL)
    private List<SchoolAdmin> schoolAdmins;

}
