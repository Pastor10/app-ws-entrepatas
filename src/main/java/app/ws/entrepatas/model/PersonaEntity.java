package app.ws.entrepatas.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table(name = "persona")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long id;

    @Column(name = "correo")
    public String correo;

    @Column(name = "nombre")
    public String nombre;


    @Column(name = "ape_paterno")
    private String apePaterno;

    @Column(name = "ape_materno")
    private String apeMaterno;

    @Column(name = "nombre_completo")
    private String nombreCompleto;


    @Column(name = "celular")
    public String celular;

    @Column(name = "direccion")
    public String direccion;

    @Column(name = "fecha_nacimiento")
    public LocalDate fechaNacimiento;

    @Column(name = "is_completed")
    public Boolean isCompleted;

    @Column(name = "foto")
    public String foto;


    @OneToOne
    @JoinColumn(name = "id_tipo_documento")
    public TipoDocumentoEntity tipoDocumento;

    @Column(name = "numero_documento")
    public String numeroDocumento;

    @OneToOne
    @JoinColumn(name = "id_ubigeo")
    public UbigeoEntity ubigeo;


//    @OneToOne
//    @JoinColumn(name = "id_ocupacion")
//    public OcupacionEntity ocupacion;


    @JoinColumn(name = "ocupacion")
    public String ocupacion;

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
