package app.ws.entrepatas.repository;

import app.ws.entrepatas.model.PostulanteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PostulanteRepository extends JpaRepository<PostulanteEntity, Long> {

    List<PostulanteEntity> findAllByPublicacionId(Long id);
    List<PostulanteEntity> findAllByPersonaId(Long id);
    @Transactional
    @Modifying
    @Query("update PostulanteEntity u set u.usuarioModifica =:idUser , u.fechaModificacion=:updateOn, u.puntuacion=:puntuacion where u.id=:id")
    void update(@Param("id") Long id, @Param("idUser") Long idUser, @Param("updateOn") LocalDateTime updateOn, @Param("puntuacion") Integer puntuacion);

}
