package app.ws.entrepatas.model;


import app.ws.entrepatas.enums.TipoDocumento;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tipo_documento")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TipoDocumentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long id;

    @Column(name = "nombre")
    @Enumerated(EnumType.STRING)
    public TipoDocumento nombre;

    @Column(name = "abreviatura")
    public String abreviatura;

    @Column(name = "numero_caracter")
    public Integer numCaracter;

    @Column(name = "estado")
    public Boolean estado;

}
