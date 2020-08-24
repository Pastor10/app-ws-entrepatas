package app.ws.entrepatas.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "postulante_colaborador")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostulanteColaboradorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long id;

    @OneToOne
    @JoinColumn(name = "id_persona")
    public PersonaEntity persona;

    @OneToOne
    @JoinColumn(name = "id_cuestionario")
    public CuestionarioEntity cuestionario;


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
