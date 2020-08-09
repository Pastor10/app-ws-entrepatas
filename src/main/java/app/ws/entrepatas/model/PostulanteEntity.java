package app.ws.entrepatas.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table(name = "postulante")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostulanteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long id;

    @OneToOne
    @JoinColumn(name = "id_publicacion")
    public PublicacionEntity publicacion;


    @Column(name = "puntuacion")
    public Integer puntuacion;

    @OneToOne
    @JoinColumn(name = "id_persona")
    public PersonaEntity persona;


    @Column(name = "eliminado")
    private Boolean eliminado;

    @Column(name = "usuario_crea")
    private Long usuarioCrea;

    @Column(name = "fecha_creacion")
    public LocalDateTime fechaCreacion;

    @Column( name = "usuario_modifica")
    private Long usuarioModifica;

    @Column( name = "fecha_modificacion")
    private LocalDateTime  fechaModificacion;

    @Column( name = "usuario_elimina")
    private Long usuarioElimina;

    @Column( name = "fecha_eliminacion")
    private LocalDateTime fechaEliminacion;
}
