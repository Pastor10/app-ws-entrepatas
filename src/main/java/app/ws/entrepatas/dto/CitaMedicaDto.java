package app.ws.entrepatas.dto;

import app.ws.entrepatas.enums.EstadoClinico;
import app.ws.entrepatas.model.CitaMedicaEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.time.LocalDate;
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
public class CitaMedicaDto {

    @ApiModelProperty(readOnly = true)
    public Long id;

    public LocalDate fechaVisita;

    public VeterinarioDto veterinario;

    public String diagnostico;

    public EstadoClinico estadoClinico;

    public List<TratamientoMedicoDto> listaTratamiento;

    public String numero;

    public static CitaMedicaDto transformToDto(CitaMedicaEntity model) {
        if (model == null) return null;

        return CitaMedicaDto.builder()
                .id(model.getId())
                .fechaVisita(model.getFechaVisita())
                .veterinario(VeterinarioDto.transformToDto(model.getVeterinario()))
                .diagnostico(model.getDiagnostico())
                .estadoClinico(model.getEstadoClinico())
                .numero(model.getNumero())
                .listaTratamiento(TratamientoMedicoDto.transformToDto(model.getListaTratamiento()))
                .build();
    }

    public static List<CitaMedicaDto> transformToDto(List<CitaMedicaEntity> model) {
        if (model == null) return Collections.emptyList();
        return model.stream()
                .map(CitaMedicaDto::transformToDto).collect(Collectors.toList());
    }
}
