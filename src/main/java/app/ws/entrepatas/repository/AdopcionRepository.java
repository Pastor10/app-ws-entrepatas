package app.ws.entrepatas.repository;

import app.ws.entrepatas.enums.EstadoAdopcion;
import app.ws.entrepatas.model.AdopcionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdopcionRepository extends JpaRepository<AdopcionEntity, Long> {

    @Query("select  t from AdopcionEntity t where t.estadoAdopcion in (:estados)")
    List<AdopcionEntity> findAllAdopciones(@Param("estados") List<EstadoAdopcion> estados);
}
