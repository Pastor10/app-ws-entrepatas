package app.ws.entrepatas.repository;

import app.ws.entrepatas.model.TipoAnimalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoAnimalRepository extends JpaRepository<TipoAnimalEntity, Long> {
}
