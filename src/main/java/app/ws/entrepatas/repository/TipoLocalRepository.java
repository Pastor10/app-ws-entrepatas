package app.ws.entrepatas.repository;

import app.ws.entrepatas.model.TipoLocalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoLocalRepository extends JpaRepository<TipoLocalEntity, Long> {
}
