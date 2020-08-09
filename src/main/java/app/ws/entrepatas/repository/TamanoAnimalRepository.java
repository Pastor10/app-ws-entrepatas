package app.ws.entrepatas.repository;

import app.ws.entrepatas.model.TamanoAnimalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TamanoAnimalRepository  extends JpaRepository<TamanoAnimalEntity, Long> {
}
