package app.ws.entrepatas.model;

import app.ws.entrepatas.enums.CondicionAnimal;
import app.ws.entrepatas.enums.EstadoCivil;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "condicion")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CondicionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long id;

    @Column(name = "nombre")
    @Enumerated(EnumType.STRING)
    public CondicionAnimal nombre;

    @Column(name = "estado")
    public Boolean estado;

    @Column(name = "fecha_creacion")
    public LocalDateTime fechaCreacion;
}
