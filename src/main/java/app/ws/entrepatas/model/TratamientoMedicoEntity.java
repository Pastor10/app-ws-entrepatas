package app.ws.entrepatas.model;

import app.ws.entrepatas.enums.EstadoClinico;
import lombok.*;
import org.springframework.cglib.core.Local;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "tratamiento_medico")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TratamientoMedicoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long id;

    @Column(name = "medicina")
    public String medicina;

    @Column(name = "cantidad")
    public Integer cantidad;

    @Column(name = "unidad_medida")
    public String unidadMedida;

    @ManyToOne
    @JoinColumn(name = "cita_medica_id")
    private CitaMedicaEntity citaMedica;

}
