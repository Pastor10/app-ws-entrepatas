package app.ws.entrepatas.repository;

import app.ws.entrepatas.model.CitaMedicaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitaMedicaRepository extends JpaRepository<CitaMedicaEntity, Long> {
}
