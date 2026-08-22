package john.patrick.laplana.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "school_admin")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class SchoolAdmin extends BaseEntity {
    
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "full_name", nullable = false, length = 200)
    private String fullName;

    @ManyToOne
    @JoinColumn(name = "school_id", nullable = false)
    private School school;

}
