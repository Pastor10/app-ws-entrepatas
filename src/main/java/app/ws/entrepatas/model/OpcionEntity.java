package app.ws.entrepatas.model;

import app.ws.entrepatas.dto.DetalleCuestionarioDto;
import app.ws.entrepatas.dto.OpcionDto;
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
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "opcion")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OpcionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public  Long id;

    @Column(name = "nombre")
    public String nombre;

    @Column(name = "puntaje")
    public Integer puntaje;

    public static OpcionEntity transformToModel(OpcionDto dto) {
        if (dto == null) return null;

        return OpcionEntity.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .puntaje(dto.getPuntaje())
                .build();
    }

    public static List<OpcionEntity> transformToModel(List<OpcionDto> model) {
        if (model == null) return Collections.emptyList();
        return model.stream().map(OpcionEntity::transformToModel).collect(Collectors.toList());
    }
}
