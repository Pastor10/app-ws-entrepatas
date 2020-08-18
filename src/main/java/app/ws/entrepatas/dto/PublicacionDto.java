package app.ws.entrepatas.dto;

import app.ws.entrepatas.enums.CondicionAnimal;
import app.ws.entrepatas.enums.EstadoPublicacion;
import app.ws.entrepatas.model.CondicionEntity;
import app.ws.entrepatas.model.PersonaEntity;
import app.ws.entrepatas.model.PublicacionEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.Column;
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
public class PublicacionDto {

    public  Long id;

    public String descripcion;

    public AnimalDto animal;

    public UsuarioDto usuarioPublica;

    public UsuarioDto usuarioEvalua;

    public EstadoPublicacion estadoPublicacion;

    public CondicionEntity condicion;

    public LocalDateTime fechaCreacion;

    public String observacion;

    public static PublicacionDto transformToDto(PublicacionEntity model) {
        if (model == null) return null;

        return PublicacionDto.builder()
                .id(model.getId())
                .descripcion(model.getDescripcion())
                .animal(AnimalDto.transformToDto(model.getAnimal()))
                .usuarioPublica(UsuarioDto.transformToDto(model.getUsuarioPublica()))
                .usuarioEvalua(UsuarioDto.transformToDto(model.getUsuarioEvalua()))
                .estadoPublicacion(model.getEstadoPublicacion())
                .observacion(model.getObservacion())
                .condicion(CondicionEntity.builder().id(model.getCondicion().getId()).nombre(model.getCondicion().getNombre()).build())
                .fechaCreacion(model.getFechaCreacion())
                .build();
    }

    public static List<PublicacionDto> transformToDto(List<PublicacionEntity> model) {
        if (model == null) return Collections.emptyList();
        return model.stream()
                .map(PublicacionDto::transformToDto).collect(Collectors.toList());
    }

    public static List<PublicacionDto> transformToDtoAprobados(List<PublicacionEntity> model) {
        if (model == null) return Collections.emptyList();
        return model.stream()
                .filter(it-> it.getEstadoPublicacion()==EstadoPublicacion.APROBADO)
                .map(PublicacionDto::transformToDto).collect(Collectors.toList());
    }

    public static List<PublicacionDto> transformToDtoCondicionAdopcion(List<PublicacionEntity> model) {
        if (model == null) return Collections.emptyList();
        return model.stream()
                .filter(it-> it.getCondicion().getNombre().equals(CondicionAnimal.ADOPCION) && it.getEstadoPublicacion().equals(EstadoPublicacion.APROBADO))
                .map(PublicacionDto::transformToDto).collect(Collectors.toList());
    }
}
