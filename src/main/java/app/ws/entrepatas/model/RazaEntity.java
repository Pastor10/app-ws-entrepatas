package app.ws.entrepatas.model;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "raza")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RazaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long id;

    @ManyToOne
    @JoinColumn(name = "id_tipo_animal")
    public TipoAnimalEntity tipoAnimal;

    @Column(name = "nombre")
    public String nombre;

    @Column(name = "estado")
    public Boolean estado;

    @Column(name = "fecha_creacion")
    public LocalDateTime fechaCreacion;

}
