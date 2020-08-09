package app.ws.entrepatas.model;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "ubigeo")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UbigeoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long id;

    @Column(name = "cod_departamento")
    public String codDepartamento;

    @Column(name = "cod_provincia")
    public String codProvincia;

    @Column(name = "cod_distrito")
    public String codDistrito;

    @Column(name = "nombre")
    public String nombre;

    @Transient
    private String descripcion;
}
