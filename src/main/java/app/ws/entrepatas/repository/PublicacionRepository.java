package app.ws.entrepatas.repository;

import app.ws.entrepatas.enums.EstadoPublicacion;
import app.ws.entrepatas.model.PublicacionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PublicacionRepository extends JpaRepository<PublicacionEntity, Long> {

    List<PublicacionEntity> findAllByUsuarioPublica_Id(Long id);

    PublicacionEntity findByAnimal_Id(Long id);

    @Query(value = "select p from PublicacionEntity p " +
            " join p.usuarioPublica u " +
            " join u.perfil pe " +
            " where p.eliminado = false and not pe.nombre =:perfil")
    Page<PublicacionEntity> findAllPublicaciones(@Param("perfil") String perfil, Pageable pageable);

    @Query(value = "select p from PublicacionEntity p " +
            " join p.usuarioPublica u " +
            " join u.perfil pe " +
            " where p.eliminado = false and  pe.nombre =:perfil")
    Page<PublicacionEntity> findAllPublicacionesVisitantes(@Param("perfil") String perfil, Pageable pageable);

}
