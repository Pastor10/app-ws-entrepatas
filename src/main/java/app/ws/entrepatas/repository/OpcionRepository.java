package app.ws.entrepatas.repository;

import app.ws.entrepatas.model.OpcionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpcionRepository extends JpaRepository<OpcionEntity, Long> {
}
