package app.ws.entrepatas.repository;

import app.ws.entrepatas.model.TipoEventoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoEventoRepository extends JpaRepository<TipoEventoEntity, Long> {
}
