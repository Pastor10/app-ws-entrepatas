package app.ws.entrepatas.repository;

import app.ws.entrepatas.model.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    //Optional<UsuarioEntity> findByCorreo(String email);
    Optional<UsuarioEntity> findByUsername(String username);
    UsuarioEntity findByPersonaNumeroDocumento(String documento);

    @Transactional
    @Modifying
    @Query("update UsuarioEntity u set u.eliminado = true, u.usuarioElimina=:idUser , u.fechaEliminacion=:deleteOn where u.id=:id")
    void delete(@Param("id") Long id, @Param("idUser") Long idUser,  @Param("deleteOn") LocalDateTime deleteOn);


}
