package app.ws.entrepatas.model;

import app.ws.entrepatas.enums.EstadoClinico;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cita_medica")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CitaMedicaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long id;


    @Column(name = "fecha_visita")
    public LocalDate fechaVisita;

    @ManyToOne
    @JoinColumn(name = "id_veterinario")
    public VeterinarioEntity veterinario;

    @Column(name = "diagnostico")
    public String diagnostico;

    @Column(name = "numero")
    public String numero;

    @Column(name = "estado_clinico")
    @Enumerated(EnumType.STRING)
    public EstadoClinico estadoClinico;

    @Column(name = "foto")
    public String foto;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "citaMedica", cascade = CascadeType.ALL)
    private List<TratamientoMedicoEntity> listaTratamiento;

    @ManyToOne
    @JoinColumn(name = "id_animal")
    private AnimalEntity animal;


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
