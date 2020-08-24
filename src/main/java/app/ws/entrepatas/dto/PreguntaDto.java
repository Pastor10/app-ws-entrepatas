package app.ws.entrepatas.dto;

import app.ws.entrepatas.model.PreguntaEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
public class PreguntaDto {

    public  Long id;
    public String nombre;

    public static PreguntaDto transformToDto(PreguntaEntity model) {
        if (model == null) return null;

        return PreguntaDto.builder()
                .id(model.getId())
                .nombre(model.getNombre())
                .build();
    }

    public static List<PreguntaDto> transformToDto(List<PreguntaEntity> model) {
        if (model == null) return Collections.emptyList();
        return model.stream()
                .map(PreguntaDto::transformToDto).collect(Collectors.toList());
    }
}
