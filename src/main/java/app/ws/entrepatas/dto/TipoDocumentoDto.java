package app.ws.entrepatas.dto;

import app.ws.entrepatas.enums.TipoDocumento;
import app.ws.entrepatas.model.TipoDocumentoEntity;
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
public class TipoDocumentoDto {

    public Long id;

    public TipoDocumento nombre;

    public String abreviatura;

    public Integer numCaracter;

    public static TipoDocumentoDto transformToDto(TipoDocumentoEntity model) {
        if (model == null) return null;

        return TipoDocumentoDto.builder()
                .id(model.getId())
                .nombre(model.getNombre())
                .abreviatura(model.getAbreviatura())
                .numCaracter(model.getNumCaracter())
                .build();
    }

    public static List<TipoDocumentoDto> transformToDto(List<TipoDocumentoEntity> model) {
        if (model == null) return Collections.emptyList();
        return model.stream()
                .map(TipoDocumentoDto::transformToDto).collect(Collectors.toList());
    }
}
