package app.ws.entrepatas.dto;

import app.ws.entrepatas.model.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.data.domain.Page;

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
public class UsuarioDto {

    @ApiModelProperty(readOnly = true)
    public Long id;
    private String username;
    public PersonaDto persona;
    public PerfilDto perfil;
    public Boolean estado;
    public LocalDateTime fechaCreacion;

    public static UsuarioDto transformToDto(UsuarioEntity model) {
        if (model == null) return null;

        return UsuarioDto.builder()
                .id(model.getId())
                .username(model.getUsername())
                .persona(PersonaDto.transformToDto(model.getPersona()))
                .perfil(PerfilDto.transformToDto(model.getPerfil()))
                .estado(model.getEstado())
                .fechaCreacion(model.getFechaCreacion())
                .build();
    }

    public static List<UsuarioDto> transformToDto(List<UsuarioEntity> model) {
        if (model == null) return Collections.emptyList();
        return model.stream().filter(user -> !user.getEliminado())
                .map(UsuarioDto::transformToDto).collect(Collectors.toList());
    }

    public static List<UsuarioDto> transformToDtoIntegrantes(List<UsuarioEntity> model) {
        if (model == null) return Collections.emptyList();
        return model.stream().filter(user -> !user.getEliminado() && !user.getPerfil().getNombre().equals("VISITANTE"))
                .map(UsuarioDto::transformToDto).collect(Collectors.toList());
    }
}
