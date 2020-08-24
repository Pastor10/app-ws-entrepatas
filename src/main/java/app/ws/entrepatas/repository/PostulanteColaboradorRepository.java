package app.ws.entrepatas.repository;

import app.ws.entrepatas.model.PostulanteColaboradorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostulanteColaboradorRepository extends JpaRepository<PostulanteColaboradorEntity, Long> {
}
