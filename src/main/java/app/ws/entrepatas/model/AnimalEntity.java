package app.ws.entrepatas.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "animal")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnimalEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  public  Long id;

  @Column(name = "nombre")
  public String nombre;

  @ManyToOne
  @JoinColumn(name = "id_tamano")
  public TamanoAnimalEntity tamanoAnimal;

  @ManyToOne
  @JoinColumn(name = "id_raza")
  public RazaEntity raza;

  @Column(name = "sexo")
  public String sexo;

  @Column(name = "edad")
  public String edad;

  @ManyToOne
  @JoinColumn(name = "id_local")
  public LocalEntity local;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "animal", cascade = CascadeType.ALL)
  private List<CitaMedicaEntity> citasMedicas;

  @Column(name = "fecha_creacion")
  public LocalDateTime fechaCreacion;

  @Column(name = "foto")
  public String foto;

  @Column(name = "estado")
  public Boolean estado;

  @Column(name = "disponible")
  public Boolean disponible;

}
