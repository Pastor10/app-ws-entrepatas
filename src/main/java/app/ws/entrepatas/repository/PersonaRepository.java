package app.ws.entrepatas.repository;

import app.ws.entrepatas.model.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository<PersonaEntity, Long> {

    PersonaEntity findByNumeroDocumento(String documento);
}
