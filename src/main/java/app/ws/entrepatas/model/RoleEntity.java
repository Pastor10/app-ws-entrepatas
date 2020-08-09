package app.ws.entrepatas.model;

import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;


@Data
@Entity
@Table(name = "roles")
@AllArgsConstructor
@Builder
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
    private RoleName name;


    public RoleEntity() {
    }

    public RoleEntity(RoleName name) {
        this.name = name;
    }

}
