package app.ws.entrepatas.repository;

import app.ws.entrepatas.model.CuestionarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuestionarioRepository extends JpaRepository<CuestionarioEntity, Long> {
}
