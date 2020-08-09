package app.ws.entrepatas.repository;

import app.ws.entrepatas.model.UbigeoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UbigeoRepository extends JpaRepository<UbigeoEntity, Long> {
    UbigeoEntity findByCodDepartamentoAndCodProvinciaAndCodDistrito(String cdpto, String cdpro, String cdist);

    List<UbigeoEntity> findFirst20ByNombreContainingAndCodProvinciaNotAndCodDistritoNot(String nombre, String notCodPro, String notCodDi);
}
