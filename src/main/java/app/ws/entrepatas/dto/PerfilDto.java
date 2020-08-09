package app.ws.entrepatas.dto;

import app.ws.entrepatas.model.PerfilEntity;
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
public class PerfilDto {

    private Long id;
    private String nombre;
    private Boolean activo;

    public static PerfilDto transformToDto(PerfilEntity model) {
        if (model == null) return null;

        return PerfilDto.builder()
                .id(model.getId())
                .nombre(model.getNombre())
                .build();
    }

    public static List<PerfilDto> transformToDto(List<PerfilEntity> model) {
        if (model == null) return Collections.emptyList();
        return model.stream()
                .map(PerfilDto::transformToDto).collect(Collectors.toList());
    }
}
