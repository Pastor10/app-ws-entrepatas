package app.ws.entrepatas.repository;

import app.ws.entrepatas.model.PostulanteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostulanteRepository extends JpaRepository<PostulanteEntity, Long> {

    List<PostulanteEntity> findAllByPublicacionId(Long id);
    List<PostulanteEntity> findAllByPersonaId(Long id);
}
