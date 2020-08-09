package app.ws.entrepatas.dto;

import app.ws.entrepatas.model.PerfilEntity;
import app.ws.entrepatas.model.TipoAnimalEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TipoAnimalDto {

    public Long id;
    public String nombre;
    public Boolean estado;

    public static TipoAnimalDto transformToDto(TipoAnimalEntity model) {
        if (model == null) return null;

        return TipoAnimalDto.builder()
                .id(model.getId())
                .nombre(model.getNombre())
                .estado(model.getEstado())
                .build();
    }

    public static List<TipoAnimalDto> transformToDto(List<TipoAnimalEntity> model) {
        if (model == null) return Collections.emptyList();
        return model.stream()
                .map(TipoAnimalDto::transformToDto).collect(Collectors.toList());
    }
}
