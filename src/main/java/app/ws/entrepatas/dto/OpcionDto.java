package app.ws.entrepatas.dto;

import app.ws.entrepatas.model.OpcionEntity;
import app.ws.entrepatas.model.TipoCuestionarioEntity;
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
public class OpcionDto {

    public  Long id;
    public String nombre;
    public Integer puntaje;

    public static OpcionDto transformToDto(OpcionEntity model) {
        if (model == null) return null;

        return OpcionDto.builder()
                .id(model.getId())
                .nombre(model.getNombre())
                .puntaje(model.getPuntaje())
                .build();
    }

    public static List<OpcionDto> transformToDto(List<OpcionEntity> model) {
        if (model == null) return Collections.emptyList();
        return model.stream()
                .map(OpcionDto::transformToDto).collect(Collectors.toList());
    }
}
