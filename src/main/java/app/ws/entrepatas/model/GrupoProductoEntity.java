package app.ws.entrepatas.model;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "grupo_producto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GrupoProductoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long id;

    @Column(name = "nombre")
    public String nombre;

    @Column(name = "estado")
    public Boolean estado;
}
