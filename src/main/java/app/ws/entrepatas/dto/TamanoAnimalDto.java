package app.ws.entrepatas.dto;

import app.ws.entrepatas.model.RazaEntity;
import app.ws.entrepatas.model.TamanoAnimalEntity;
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
public class TamanoAnimalDto {

    public Long id;

    public String nombre;

    public Boolean estado;

    public static TamanoAnimalDto transformToDto(TamanoAnimalEntity model) {
        if (model == null) return null;

        return TamanoAnimalDto.builder()
                .id(model.getId())
                .nombre(model.getNombre())
                .estado(model.getEstado())
                .build();
    }

    public static List<TamanoAnimalDto> transformToDto(List<TamanoAnimalEntity> model) {
        if (model == null) return Collections.emptyList();
        return model.stream()
                .map(TamanoAnimalDto::transformToDto).collect(Collectors.toList());
    }
}
