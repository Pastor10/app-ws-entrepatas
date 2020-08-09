package app.ws.entrepatas.repository;

import app.ws.entrepatas.model.VeterinarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Repository
public interface VeterinarioRepository extends JpaRepository<VeterinarioEntity, Long> {

    @Transactional
    @Modifying
    @Query("update VeterinarioEntity u set u.eliminado = true, u.usuarioElimina=:idUser , u.fechaEliminacion=:deleteOn where u.id=:id")
    void delete(@Param("id") Long id, @Param("idUser") Long idUser, @Param("deleteOn") LocalDateTime deleteOn);
}
