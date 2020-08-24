package app.ws.entrepatas.repository;

import app.ws.entrepatas.model.UsuarioEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    //Optional<UsuarioEntity> findByCorreo(String email);
    Optional<UsuarioEntity> findByUsername(String username);
    Optional<UsuarioEntity> findByUuid(String uuid);
    UsuarioEntity findByPersonaNumeroDocumento(String documento);

    @Query(value = "select u from UsuarioEntity u " +
            " join u.persona p " +
            " where u.eliminado = false " +
            " and (:nombres is null or (p.nombre like concat('%', :nombres, '%') ) ) " +
            " and (:documento is null or p.numeroDocumento=:documento)")

    Page<UsuarioEntity> findAllUsers(@Param("nombres") String nombres, @Param("documento") String documento, Pageable pageable);

    @Query(value = "select u from UsuarioEntity u " +
            " inner join  u.perfil p " +
            " where u.eliminado = false and p.nombre = 'VISITANTE'")
    Page<UsuarioEntity> findAllIntegrantes(Pageable pageable);

    @Transactional
    @Modifying
    @Query("update UsuarioEntity u set u.eliminado = true, u.usuarioElimina=:idUser , u.fechaEliminacion=:deleteOn where u.id=:id")
    void delete(@Param("id") Long id, @Param("idUser") Long idUser,  @Param("deleteOn") LocalDateTime deleteOn);


}
