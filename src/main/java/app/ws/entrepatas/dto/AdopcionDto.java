package app.ws.entrepatas.dto;

import app.ws.entrepatas.enums.EstadoAdopcion;
import app.ws.entrepatas.model.AdopcionEntity;
import app.ws.entrepatas.model.AnimalEntity;
import app.ws.entrepatas.model.PersonaEntity;
import app.ws.entrepatas.model.TamanoAnimalEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
public class AdopcionDto {

    public  Long id;

    public AnimalDto animal;

    public PersonaDto persona;

    public LocalDate fechaAdopcion;

    public EstadoAdopcion estadoAdopcion;

    public LocalDateTime fechaCreacion;

    public static AdopcionDto transformToDto(AdopcionEntity model) {
        if (model == null) return null;

        return AdopcionDto.builder()
                .id(model.getId())
                .animal(AnimalDto.transformToDto(model.getAnimal()))
                .persona(PersonaDto.transformToDto(model.getPersona()))
                .fechaAdopcion(model.getFechaAdopcion())
                .estadoAdopcion(model.getEstadoAdopcion())
                .fechaCreacion(model.getFechaCreacion())
                .build();
    }

    public static List<AdopcionDto> transformToDto(List<AdopcionEntity> model) {
        if (model == null) return Collections.emptyList();
        return model.stream()
                .map(AdopcionDto::transformToDto).collect(Collectors.toList());
    }
}
