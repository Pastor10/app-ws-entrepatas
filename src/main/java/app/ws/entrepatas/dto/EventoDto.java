package app.ws.entrepatas.dto;

import app.ws.entrepatas.model.EventoEntity;
import app.ws.entrepatas.model.TipoEventoEntity;
import app.ws.entrepatas.model.UbigeoEntity;
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
public class EventoDto {

    public Long id;

    public String titulo;

    public TipoEventoEntity tipoEvento;

    public String descripcion;

    public LocalDateTime fecha;

    public String direccion;

    public String banner;

    public UbigeoEntity ubigeo;

    public Boolean estado;

    private Boolean eliminado;

    public static EventoDto transformToDto(EventoEntity model) {
        if (model == null) return null;

        return EventoDto.builder()
                .id(model.getId())
                .titulo(model.titulo)
                .tipoEvento(model.getTipoEvento())
                .descripcion(model.getDescripcion())
                .fecha(model.getFecha())
                .direccion(model.getDireccion())
                .banner(model.getBanner())
                .ubigeo(model.getUbigeo())
                .eliminado(model.getEliminado())
                .estado(model.getEstado())
                .build();
    }

    public static List<EventoDto> transformToDto(List<EventoEntity> model) {
        if (model == null) return Collections.emptyList();
        return model.stream().filter(it->!it.getEliminado())
                .map(EventoDto::transformToDto).collect(Collectors.toList());
    }

}
