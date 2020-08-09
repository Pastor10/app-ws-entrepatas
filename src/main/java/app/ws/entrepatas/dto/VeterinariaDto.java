package app.ws.entrepatas.dto;

import app.ws.entrepatas.model.UbigeoEntity;
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
public class VeterinariaDto {

    public Long id;

    public String nombre;

    public String direccion;

    public UbigeoEntity ubigeo;

    public Boolean estado;

    public static VeterinariaDto transformToDto(VeterinariaEntity model) {
        if (model == null) return null;

        return VeterinariaDto.builder()
                .id(model.getId())
                .nombre(model.getNombre())
                .direccion(model.getDireccion())
                .ubigeo(UbigeoEntity.builder().id(model.getUbigeo().getId()).build())
                .estado(model.getEstado())
                .build();
    }

    public static List<VeterinariaDto> transformToDto(List<VeterinariaEntity> model) {
        if (model == null) return Collections.emptyList();
        return model.stream()
                .map(VeterinariaDto::transformToDto).collect(Collectors.toList());
    }
}
