package app.ws.entrepatas.service;

import app.ws.entrepatas.exception.NoExistEntityException;
import app.ws.entrepatas.model.TipoAnimalEntity;

import java.util.List;
import java.util.Optional;

public interface TipoAnimalService {
    TipoAnimalEntity create(TipoAnimalEntity o);
    TipoAnimalEntity update(TipoAnimalEntity o) throws NoExistEntityException;
    void delete(Long id) throws NoExistEntityException;
    Optional<TipoAnimalEntity> findById(Long id);
    List<TipoAnimalEntity> findAll();
}
