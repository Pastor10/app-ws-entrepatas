package app.ws.entrepatas.dto;

import app.ws.entrepatas.enums.CondicionAnimal;
import app.ws.entrepatas.model.CondicionEntity;
import app.ws.entrepatas.model.EventoEntity;
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
public class CondicionDto {

    public Long id;
    public CondicionAnimal nombre;
    public Boolean estado;

    public static CondicionDto transformToDto(CondicionEntity model) {
        if (model == null) return null;

        return CondicionDto.builder()
                .id(model.getId())
                .nombre(model.getNombre())
                .estado(model.getEstado())
                .build();
    }

    public static List<CondicionDto> transformToDto(List<CondicionEntity> model) {
        if (model == null) return Collections.emptyList();
        return model.stream().filter(it->!it.getNombre().equals(CondicionAnimal.ADOPCION))
                .map(CondicionDto::transformToDto).collect(Collectors.toList());
    }
}
