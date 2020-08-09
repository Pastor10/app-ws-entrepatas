package app.ws.entrepatas.dto;

import app.ws.entrepatas.model.CitaMedicaEntity;
import app.ws.entrepatas.model.RazaEntity;
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
public class RazaDto {

    public Long id;
    public TipoAnimalDto tipoAnimal;
    public String nombre;

    public static RazaDto transformToDto(RazaEntity model) {
        if (model == null) return null;

        return RazaDto.builder()
                .id(model.getId())
                .tipoAnimal(TipoAnimalDto.transformToDto(model.getTipoAnimal()))
                .nombre(model.getNombre())
                .build();
    }

    public static List<RazaDto> transformToDto(List<RazaEntity> model) {
        if (model == null) return Collections.emptyList();
        return model.stream()
                .map(RazaDto::transformToDto).collect(Collectors.toList());
    }
}
