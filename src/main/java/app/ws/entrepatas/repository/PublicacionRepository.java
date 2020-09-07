package app.ws.entrepatas.repository;

import app.ws.entrepatas.enums.CondicionAnimal;
import app.ws.entrepatas.enums.EstadoPublicacion;
import app.ws.entrepatas.model.PublicacionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface PublicacionRepository extends JpaRepository<PublicacionEntity, Long> {

    List<PublicacionEntity> findAllByUsuarioPublica_Id(Long id);

    PublicacionEntity findByAnimal_Id(Long id);

    @Query(value = "select p from PublicacionEntity p " +
            "  join p.usuarioPublica u " +
            " join u.perfil pe " +
            " join p.condicion c " +
            " where p.eliminado = false and pe.id not in (:idPerfil) " +
            " and (COALESCE(:condicion) is null or c.nombre in (:condicion))" +
            " and ((:fechaDesde is null or :fechaHasta is null) or (p.fechaCreacion between :fechaDesde and :fechaHasta)) ")
    Page<PublicacionEntity> findAllPublicaciones(@Param("fechaDesde") LocalDate fechaDesde, @Param("fechaHasta") LocalDate fechaHasta, @Param("condicion") List<CondicionAnimal> condicion,
                                                 @Param("idPerfil") Long idPerfil, Pageable pageable);

    @Query(value = "select p from PublicacionEntity p " +
            " join p.usuarioPublica u " +
            " join u.perfil pe " +
            " where p.eliminado = false and  pe.id =:idPerfil")
    Page<PublicacionEntity> findAllPublicacionesVisitantes(@Param("idPerfil") Long idPerfil, Pageable pageable);


}
