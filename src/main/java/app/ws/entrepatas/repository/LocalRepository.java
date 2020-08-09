package app.ws.entrepatas.repository;

import app.ws.entrepatas.model.LocalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LocalRepository extends JpaRepository<LocalEntity, Long> {
}
