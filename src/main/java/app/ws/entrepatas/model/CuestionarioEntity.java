package app.ws.entrepatas.model;

import app.ws.entrepatas.dto.CuestionarioDto;
import app.ws.entrepatas.dto.DetalleCuestionarioDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Entity
@Table(name = "cuestionario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CuestionarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public  Long id;


    @Column(name = "promedio")
    private Integer promedio;

//    @ManyToOne
//    @JoinColumn(name = "id_postulante")
//    public PostulanteEntity postulante;

    @OneToOne
    @JoinColumn(name = "id_tipo_cuestionario")
    public TipoCuestionarioEntity tipoCuestionario;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cuestionario", cascade = CascadeType.ALL)
    private List<DetalleCuestionarioEntity> listaDetalle;

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

    @Transient
    private Long idPostulante;


    public static CuestionarioEntity transformToModel(CuestionarioDto dto) {
        if (dto == null) return null;

        return CuestionarioEntity.builder()
                .id(dto.getId())
                .promedio(dto.getPromedio())
                .tipoCuestionario(TipoCuestionarioEntity.transformToModel(dto.getTipoCuestionario()))
                .listaDetalle(DetalleCuestionarioEntity.transformToModel(dto.getListaDetalle()))
                .build();
    }

    public static List<CuestionarioEntity> transformToModel(List<CuestionarioDto> model) {
        if (model == null) return Collections.emptyList();
        return model.stream().map(CuestionarioEntity::transformToModel).collect(Collectors.toList());
    }


}
