package app.ws.entrepatas.dto;

import app.ws.entrepatas.model.PostulanteColaboradorEntity;
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
public class PostulanteColaboradorDto {

    public Long id;

    public PersonaDto persona;

    private Boolean eliminado;

    private CuestionarioDto cuestionario;

    public static PostulanteColaboradorDto transformToDto(PostulanteColaboradorEntity model) {
        if (model == null) return null;

        return PostulanteColaboradorDto.builder()
                .id(model.getId())
                .persona(PersonaDto.transformToDto(model.getPersona()))
                .eliminado(model.getEliminado())
                .cuestionario(CuestionarioDto.transformToDto(model.getCuestionario()))
                .build();
    }

    public static List<PostulanteColaboradorDto> transformToDto(List<PostulanteColaboradorEntity> model) {
        if (model == null) return Collections.emptyList();
        return model.stream()
                .map(PostulanteColaboradorDto::transformToDto).collect(Collectors.toList());
    }
}
