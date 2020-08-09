package app.ws.entrepatas.repository;

import app.ws.entrepatas.model.PublicacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicacionRepository extends JpaRepository<PublicacionEntity, Long> {

    List<PublicacionEntity> findAllByUsuarioPublica_Id(Long id);

    PublicacionEntity findByAnimal_Id(Long id);
}
