package app.ws.entrepatas.dto;

import app.ws.entrepatas.model.CuestionarioEntity;
import app.ws.entrepatas.model.DetalleCuestionarioEntity;
import app.ws.entrepatas.model.OpcionEntity;
import app.ws.entrepatas.model.PostulanteEntity;
import app.ws.entrepatas.model.PreguntaEntity;
import app.ws.entrepatas.model.TipoCuestionarioEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
public class CuestionarioDto {

    public  Long id;

    private Integer promedio;

    public PostulanteDto postulante;

    public TipoCuestionarioDto tipoCuestionario;

    private List<DetalleCuestionarioDto> listaDetalle;


    public static CuestionarioDto transformToDto(CuestionarioEntity model) {
        if (model == null) return null;

        return CuestionarioDto.builder()
                .id(model.getId())
                .promedio(model.getPromedio())
                .tipoCuestionario(TipoCuestionarioDto.transformToDto(model.getTipoCuestionario()))
                .listaDetalle(DetalleCuestionarioDto.transformToDto(model.getListaDetalle()))
                .build();
    }

    public static List<CuestionarioDto> transformToDto(List<CuestionarioEntity> model) {
        if (model == null) return Collections.emptyList();
        return model.stream().filter(item-> !item.getEliminado())
                .map(CuestionarioDto::transformToDto).collect(Collectors.toList());
    }

}
