package app.ws.entrepatas.repository;

import app.ws.entrepatas.model.TratamientoMedicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TratamientoMedicoRepository extends JpaRepository<TratamientoMedicoEntity, Long> {
}
