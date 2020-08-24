package app.ws.entrepatas.dto;


import app.ws.entrepatas.model.TipoCuestionarioEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


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
public class TipoCuestionarioDto {

    public Long id;

    public String nombre;

    public LocalDateTime fechaCreacion;

    public static TipoCuestionarioDto transformToDto(TipoCuestionarioEntity model) {
        if (model == null) return null;

        return TipoCuestionarioDto.builder()
                .id(model.getId())
                .nombre(model.getNombre())
                .fechaCreacion(model.getFechaCreacion())
                .build();
    }

    public static List<TipoCuestionarioDto> transformToDto(List<TipoCuestionarioEntity> model) {
        if (model == null) return Collections.emptyList();
        return model.stream()
                .map(TipoCuestionarioDto::transformToDto).collect(Collectors.toList());
    }
}
