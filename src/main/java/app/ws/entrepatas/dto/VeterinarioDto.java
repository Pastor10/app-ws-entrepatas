package app.ws.entrepatas.dto;

import app.ws.entrepatas.model.CitaMedicaEntity;
import app.ws.entrepatas.model.VeterinariaEntity;
import app.ws.entrepatas.model.VeterinarioEntity;
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
public class VeterinarioDto {

    public Long id;

    public String nombre;

    public VeterinariaDto veterinaria;

    public Boolean estado;

    public static VeterinarioDto transformToDto(VeterinarioEntity model) {
        if (model == null) return null;

        return VeterinarioDto.builder()
                .id(model.getId())
                .nombre(model.getNombre())
                .veterinaria(VeterinariaDto.transformToDto(model.getVeterinaria()))
                .estado(model.getEstado())
                .build();
    }

    public static List<VeterinarioDto> transformToDto(List<VeterinarioEntity> model) {
        if (model == null) return Collections.emptyList();
        return model.stream()
                .map(VeterinarioDto::transformToDto).collect(Collectors.toList());
    }
}
