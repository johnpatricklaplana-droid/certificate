package john.patrick.laplana.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "platform_admin")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class PlatformAdmin extends BaseEntity {
    
    @Column(name = "email", nullable = false, length = 150)
    private String email;

    @Column(name = "password", nullable = false, length = 200)
    private String password;

    @Column(name = "fullname", nullable = false, length = 150)
    private String fullname;

    @Column(name = "role", nullable = false)
    private String role;

}
