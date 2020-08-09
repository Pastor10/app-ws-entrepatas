package app.ws.entrepatas.service;

import app.ws.entrepatas.exception.NoExistEntityException;
import app.ws.entrepatas.model.TamanoAnimalEntity;

import java.util.List;
import java.util.Optional;

public interface TamanoAnimalService {
    TamanoAnimalEntity create(TamanoAnimalEntity o);
    TamanoAnimalEntity update(TamanoAnimalEntity o) throws NoExistEntityException;
    void delete(Long id) throws NoExistEntityException;
    Optional<TamanoAnimalEntity> findById(Long id);
    List<TamanoAnimalEntity> findAll();
}
