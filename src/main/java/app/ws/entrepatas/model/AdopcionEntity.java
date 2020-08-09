package app.ws.entrepatas.model;


import app.ws.entrepatas.enums.EstadoAdopcion;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "adopcion")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdopcionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public  Long id;


    @OneToOne
    @JoinColumn(name = "id_animal")
    public AnimalEntity animal;

    @OneToOne
    @JoinColumn(name = "id_persona")
    public PersonaEntity persona;

//    @ManyToOne
//    @JoinColumn(name = "id_estado_adopcion")
//    public EstadoAdopcionEntity estadoAdopcion;

    @Column(name = "fecha_adopcion")
    public LocalDate fechaAdopcion;


    @Enumerated(EnumType.STRING)
    @Column(name = "estado_adopcion")
    public EstadoAdopcion estadoAdopcion;

    @Transient
    public Boolean createUser;


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
