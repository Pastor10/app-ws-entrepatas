package app.ws.entrepatas.model;

import app.ws.entrepatas.enums.EstadoPublicacion;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "publicacion")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PublicacionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public  Long id;

    @Column(name = "descripcion", length = 400)
    public String descripcion;


    @Column(name = "observacion", length = 400)
    public String observacion;

    @Column(name = "total_postulante")
    public Integer totalPostulante;

    @OneToOne
    @JoinColumn(name = "id_animal")
    public AnimalEntity animal;

    @ManyToOne
    @JoinColumn(name = "id_usuario_publica")
    public UsuarioEntity usuarioPublica;

    @ManyToOne
    @JoinColumn(name = "id_usuario_evalua")
    public UsuarioEntity usuarioEvalua;

    @Column(name = "estado_publicacion")
    @Enumerated(EnumType.STRING)
    public EstadoPublicacion estadoPublicacion;

    @ManyToOne
    @JoinColumn(name = "id_condicion")
    public CondicionEntity condicion;

    @Transient
    public String archivo;

    @Transient
    public String nombreArchivo;

    @Column(name = "eliminado")
    private Boolean eliminado;

    @Column(name = "usuario_crea")
    private Long usuarioCrea;

    @Column(name = "fecha_creacion")
    public LocalDateTime fechaCreacion;

    @Column(name = "fecha_entrega")
    public LocalDate fechaEntrega;

    @Column(name = "fecha_devolucion")
    public LocalDate fechaDevolucion;

    @Column( name = "usuario_modifica")
    private Long usuarioModifica;

    @Column( name = "fecha_modificacion")
    private LocalDateTime  fechaModificacion;

    @Column( name = "usuario_elimina")
    private Long usuarioElimina;

    @Column( name = "fecha_eliminacion")
    private LocalDateTime fechaEliminacion;

}
