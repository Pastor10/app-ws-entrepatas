package app.ws.entrepatas.dto;

import app.ws.entrepatas.model.PersonaEntity;
import app.ws.entrepatas.model.PostulanteEntity;
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
public class PostulanteDto {

    public Long id;

    public PublicacionDto publicacion;

    public Integer puntuacion;

    public PersonaDto persona;

    private Boolean eliminado;

    public static PostulanteDto transformToDto(PostulanteEntity model) {
        if (model == null) return null;

        return PostulanteDto.builder()
                .id(model.getId())
                .publicacion(PublicacionDto.transformToDto(model.getPublicacion()))
                .puntuacion(model.getPuntuacion())
                .persona(PersonaDto.transformToDto(model.getPersona()))
                .eliminado(model.getEliminado())
                .build();
    }

    public static List<PostulanteDto> transformToDto(List<PostulanteEntity> model) {
        if (model == null) return Collections.emptyList();
        return model.stream()
                .map(PostulanteDto::transformToDto).collect(Collectors.toList());
    }
}
