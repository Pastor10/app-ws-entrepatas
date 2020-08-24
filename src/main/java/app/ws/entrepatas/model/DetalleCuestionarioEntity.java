package app.ws.entrepatas.model;

import app.ws.entrepatas.dto.CuestionarioDto;
import app.ws.entrepatas.dto.DetalleCuestionarioDto;
import app.ws.entrepatas.dto.PreguntaDto;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Entity
@Table(name = "detalle_cuestionario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetalleCuestionarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public  Long id;

    @OneToOne
    @JoinColumn(name = "id_pregunta")
    public PreguntaEntity pregunta;

    @OneToOne
    @JoinColumn(name = "id_opcion")
    public OpcionEntity opcion;

    @ManyToOne
    @JoinColumn(name = "id_cuestionario")
    public CuestionarioEntity cuestionario;


    public static DetalleCuestionarioEntity transformToModel(DetalleCuestionarioDto dto) {
        if (dto == null) return null;

        return DetalleCuestionarioEntity.builder()
                .id(dto.getId())
                .pregunta(PreguntaEntity.transformToModel(dto.getPregunta()))
                .opcion(OpcionEntity.transformToModel(dto.getOpcion()))
                .build();
    }

    public static List<DetalleCuestionarioEntity> transformToModel(List<DetalleCuestionarioDto> model) {
        if (model == null) return Collections.emptyList();
        return model.stream().map(DetalleCuestionarioEntity::transformToModel).collect(Collectors.toList());
    }

}
