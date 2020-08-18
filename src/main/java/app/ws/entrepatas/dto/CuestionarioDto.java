package app.ws.entrepatas.dto;

import app.ws.entrepatas.model.CuestionarioEntity;
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
public class CuestionarioDto {

    public  Long id;

    public PreguntaEntity pregunta;

    public OpcionEntity opcion;

    private Integer promedio;


    public static CuestionarioDto transformToDto(CuestionarioEntity model) {
        if (model == null) return null;

        return CuestionarioDto.builder()
                .id(model.getId())
                .pregunta(model.getPregunta())
                .opcion(model.getOpcion())
                .promedio(model.getPromedio())
                .build();
    }

    public static List<CuestionarioDto> transformToDto(List<CuestionarioEntity> model) {
        if (model == null) return Collections.emptyList();
        return model.stream().filter(item-> !item.getEliminado())
                .map(CuestionarioDto::transformToDto).collect(Collectors.toList());
    }

}
