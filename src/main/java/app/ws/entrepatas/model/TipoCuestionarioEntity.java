package app.ws.entrepatas.model;

import app.ws.entrepatas.dto.CuestionarioDto;
import app.ws.entrepatas.dto.DetalleCuestionarioDto;
import app.ws.entrepatas.dto.TipoCuestionarioDto;
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
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "tipo_cuestionario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TipoCuestionarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long id;

    @Column(name = "nombre")
    public String nombre;

    @Column(name = "fecha_creacion")
    public LocalDateTime fechaCreacion;

    public static TipoCuestionarioEntity transformToModel(TipoCuestionarioDto dto) {
        if (dto == null) return null;

        return TipoCuestionarioEntity.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .fechaCreacion(dto.getFechaCreacion())
                .build();
    }

    public static List<TipoCuestionarioEntity> transformToModel(List<TipoCuestionarioDto> model) {
        if (model == null) return Collections.emptyList();
        return model.stream().map(TipoCuestionarioEntity::transformToModel).collect(Collectors.toList());
    }
}
