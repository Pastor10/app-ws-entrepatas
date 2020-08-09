package app.ws.entrepatas.repository;

import app.ws.entrepatas.model.CondicionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CondicionRepository extends JpaRepository<CondicionEntity, Long> {
}
