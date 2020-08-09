package app.ws.entrepatas.repository;

import app.ws.entrepatas.model.PerfilEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerfilRepository extends JpaRepository <PerfilEntity, Long> {
    //List<PerfilEntity> findByNombreIsContainingIgnoreCase(String nombre);

    PerfilEntity findByNombreIsContainingIgnoreCase(String nombre);

}
