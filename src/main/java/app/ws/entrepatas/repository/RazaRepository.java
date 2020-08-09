package app.ws.entrepatas.repository;

import app.ws.entrepatas.model.RazaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RazaRepository extends JpaRepository<RazaEntity, Long> {


    @Query("select r from RazaEntity r where r.tipoAnimal.id =:id")
    List<RazaEntity> findAllById(@Param("id")Long id);

    List<RazaEntity> findAllByTipoAnimal_Id(Long id);
}
