package app.ws.entrepatas.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


@Entity
@Table(name = "usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long id;

    @Column(name = "user_name")
    private String username;

    @Column(name = "password")
    public String password;

    @Column(name = "foto")
    public String foto;

    @Column(name = "uuid")
    public String uuid;

    @ManyToOne
    @JoinColumn(name = "id_perfil", nullable = false)
    private PerfilEntity perfil;

    @Transient
    private String passwordTrans;

    @Column(name = "estado")
    public Boolean estado;

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
