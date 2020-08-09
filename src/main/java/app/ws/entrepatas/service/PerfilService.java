package app.ws.entrepatas.service;



import app.ws.entrepatas.exception.NoExistEntityException;
import app.ws.entrepatas.model.PerfilEntity;

import java.util.List;
import java.util.Optional;

public interface PerfilService {
    PerfilEntity create(PerfilEntity o);
    PerfilEntity update(PerfilEntity o) throws NoExistEntityException;
    void delete(Long id) throws NoExistEntityException;
    Optional<PerfilEntity> findById(Long id);
    List<PerfilEntity> findAll();
    List<PerfilEntity> findByNombreIsContainingIgnoreCase(String nombre);
}
