package app.ws.entrepatas.dto;

import app.ws.entrepatas.model.CitaMedicaEntity;
import app.ws.entrepatas.model.TratamientoMedicoEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class TratamientoMedicoDto {

    public Long id;

    public String medicina;

    public Integer cantidad;

    public String unidadMedida;

    private CitaMedicaDto citaMedica;

    public static TratamientoMedicoDto transformToDto(TratamientoMedicoEntity model) {
        if (model == null) return null;

        return TratamientoMedicoDto.builder()
                .id(model.getId())
                .medicina(model.getMedicina())
                .cantidad(model.getCantidad())
                .unidadMedida(model.getUnidadMedida())
                .build();
    }

    public static List<TratamientoMedicoDto> transformToDto(List<TratamientoMedicoEntity> model) {
        if (model == null) return Collections.emptyList();
        return model.stream()
                .map(TratamientoMedicoDto::transformToDto).collect(Collectors.toList());
    }
}
