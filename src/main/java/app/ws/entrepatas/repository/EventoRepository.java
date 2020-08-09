package app.ws.entrepatas.repository;

import app.ws.entrepatas.model.EventoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository extends JpaRepository<EventoEntity, Long> {
}
