package app.ws.entrepatas.dto;

import app.ws.entrepatas.model.UbigeoEntity;
import app.ws.entrepatas.model.UsuarioEntity;
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
public class UbigeoDto {

    public Long id;
    public String codDepartamento;
    public String codProvincia;
    public String codDistrito;
    public String nombre;

    public static UbigeoDto transformToDto(UbigeoEntity model) {
        if (model == null) return null;

        return UbigeoDto.builder()
                .id(model.getId())
                .codDepartamento(model.getCodDepartamento())
                .codProvincia(model.getCodProvincia())
                .codDistrito(model.getCodDistrito())
                .nombre(model.getNombre())
                .build();
    }

    public static List<UbigeoDto> transformToDto(List<UbigeoEntity> model) {
        if (model == null) return Collections.emptyList();
        return model.stream()
                .map(UbigeoDto::transformToDto).collect(Collectors.toList());
    }
}
