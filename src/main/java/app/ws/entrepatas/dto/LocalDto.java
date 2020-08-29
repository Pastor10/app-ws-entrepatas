package app.ws.entrepatas.dto;

import app.ws.entrepatas.model.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.Column;
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
public class LocalDto {

    public Long id;
    public String nombre;
    public UsuarioDto usuario;
    public TipoLocalEntity tipoLocal;
    public UbigeoDto ubigeo;
    public Integer capacidad;
    public String direccion;
    public Boolean estado;
    public Integer alojado;
    public Boolean disponible;

    public static LocalDto transformToDto(LocalEntity model) {
        if (model == null) return null;

        return LocalDto.builder()
                .id(model.getId())
                .usuario(UsuarioDto.transformToDto(model.getUsuario()))
                .tipoLocal(TipoLocalEntity.builder().id(model.getTipoLocal().getId()).build())
                .ubigeo(UbigeoDto.transformToDto(model.getUbigeo()))
                .nombre(model.getNombre())
                .capacidad(model.getCapacidad())
                .direccion(model.getDireccion())
                .estado(model.getEstado())
                .alojado(model.getAlojado())
                .disponible(model.getDisponible())
                .build();
    }

    public static List<LocalDto> transformToDto(List<LocalEntity> model) {
        if (model == null) return Collections.emptyList();
        return model.stream().filter(it -> it.getDisponible()==null || it.getDisponible() ==true)
                .map(LocalDto::transformToDto).collect(Collectors.toList());
    }
}
