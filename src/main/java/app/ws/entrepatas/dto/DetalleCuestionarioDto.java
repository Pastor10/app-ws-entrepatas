package app.ws.entrepatas.dto;

import app.ws.entrepatas.model.CuestionarioEntity;
import app.ws.entrepatas.model.DetalleCuestionarioEntity;
import app.ws.entrepatas.model.OpcionEntity;
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
public class DetalleCuestionarioDto {

    public  Long id;
    public PreguntaDto pregunta;
    public OpcionDto opcion;


    public static DetalleCuestionarioDto transformToDto(DetalleCuestionarioEntity model) {
        if (model == null) return null;

        return DetalleCuestionarioDto.builder()
                .id(model.getId())
                .pregunta(PreguntaDto.transformToDto(model.getPregunta()))
                .opcion(OpcionDto.transformToDto(model.getOpcion()))
                .build();
    }

    public static List<DetalleCuestionarioDto> transformToDto(List<DetalleCuestionarioEntity> model) {
        if (model == null) return Collections.emptyList();
        return model.stream()
                .map(DetalleCuestionarioDto::transformToDto).collect(Collectors.toList());
    }


}
