package app.ws.entrepatas.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "local")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LocalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long id;

    @Column(name = "nombre")
    public String nombre;


    @ManyToOne
    @JoinColumn(name = "id_usuario")
    public UsuarioEntity usuario;


    @OneToOne
    @JoinColumn(name = "id_tipo_local")
    public TipoLocalEntity tipoLocal;

    @OneToOne
    @JoinColumn(name = "id_ubigeo")
    public UbigeoEntity ubigeo;

    @Column(name = "capacidad")
    public Integer capacidad;


    @Column(name = "direccion")
    public String direccion;

    @Column(name = "estado")
    public Boolean estado;

    @Column(name = "alojado")
    public Integer alojado;

    @Column(name = "disponible")
    public Boolean disponible;


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
