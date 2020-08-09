package app.ws.entrepatas.dto;

import app.ws.entrepatas.model.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
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
public class AnimalDto {

    @ApiModelProperty(readOnly = true)
    public  Long id;

    public String nombre;

    public TamanoAnimalDto tamanoAnimal;

    public RazaDto raza;

    public String sexo;

    public String edad;

    public LocalDto local;

    public String foto;

    private List<CitaMedicaDto> citasMedicas;

    public static AnimalDto transformToDto(AnimalEntity model) {
        if (model == null) return null;

        return AnimalDto.builder()
                .id(model.getId())
                .nombre(model.getNombre())
                .tamanoAnimal(TamanoAnimalDto.transformToDto(model.getTamanoAnimal()))
                .raza(RazaDto.transformToDto(model.getRaza()))
                .sexo(model.getSexo())
                .edad(model.getEdad())
                .foto(model.getFoto())
                .citasMedicas(CitaMedicaDto.transformToDto(model.getCitasMedicas()))
                .local(LocalDto.transformToDto(model.getLocal()))
                .build();
    }

    public static List<AnimalDto> transformToDto(List<AnimalEntity> model) {
        if (model == null) return Collections.emptyList();
        return model.stream()
                .map(AnimalDto::transformToDto).collect(Collectors.toList());
    }
}
