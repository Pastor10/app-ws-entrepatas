package app.ws.entrepatas.model;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tipo_evento")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TipoEventoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long id;

    @Column(name = "nombre")
    public String nombre;

    @Column(name = "imagen")
    public String imagen;

    @Column(name = "estado")
    public Boolean estado;

    @Column(name = "fecha_creacion")
    public LocalDateTime fechaCreacion;

}
