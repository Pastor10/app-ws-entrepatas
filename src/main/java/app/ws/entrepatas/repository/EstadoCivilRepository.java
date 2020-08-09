package app.ws.entrepatas.repository;

import app.ws.entrepatas.model.EstadoCivilEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoCivilRepository extends JpaRepository<EstadoCivilEntity, Long> {
}
