package app.ws.entrepatas.dto;

import app.ws.entrepatas.model.PersonaEntity;
import app.ws.entrepatas.model.UbigeoEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
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
public class PersonaDto {

    public Long id;

    public String correo;

    public String nombre;

    private String apePaterno;

    private String apeMaterno;

    private String nombreCompleto;

    public String celular;

    public String direccion;

    public LocalDate fechaNacimiento;

    public UbigeoEntity ubigeo;

    public String numeroDocumento;

    public TipoDocumentoDto tipoDocumento;

    public Boolean isCompleted;

    public String foto;

    public static String getNombresCompletos(PersonaEntity model){
        return model.getNombre().concat(" ").concat(model.getApePaterno()).concat(" ").concat(model.getApeMaterno());
    }


    public static PersonaDto transformToDto(PersonaEntity model) {
        if (model == null) return null;

        return PersonaDto.builder()
                .id(model.getId())
                .correo(model.getCorreo())
                .nombre(model.getNombre())
                .apePaterno(model.getApePaterno())
                .apeMaterno(model.getApeMaterno())
                .nombreCompleto(model.getNombreCompleto())
                .celular(model.getCelular())
                .direccion(model.getDireccion())
                .fechaNacimiento(model.getFechaNacimiento())
                .ubigeo(model.getUbigeo())
                .numeroDocumento(model.getNumeroDocumento())
                .tipoDocumento(TipoDocumentoDto.transformToDto(model.getTipoDocumento()))
                .isCompleted(model.getIsCompleted())
                .foto(model.getFoto())
                .build();
    }

    public static List<PersonaDto> transformToDto(List<PersonaEntity> model) {
        if (model == null) return Collections.emptyList();
        return model.stream()
                .map(PersonaDto::transformToDto).collect(Collectors.toList());
    }
}
