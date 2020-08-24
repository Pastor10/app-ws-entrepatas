package app.ws.entrepatas.model;

import app.ws.entrepatas.dto.OpcionDto;
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
import javax.persistence.Table;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "pregunta")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PreguntaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public  Long id;

    @Column(name = "nombre")
    public String nombre;

    public static PreguntaEntity transformToModel(PreguntaDto model) {
        if (model == null) return null;

        return PreguntaEntity.builder()
                .id(model.getId())
                .nombre(model.getNombre())
                .build();
    }

    public static List<PreguntaEntity> transformToModel(List<PreguntaDto> model) {
        if (model == null) return Collections.emptyList();
        return model.stream().map(PreguntaEntity::transformToModel).collect(Collectors.toList());
    }
}
