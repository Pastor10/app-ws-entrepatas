package app.ws.entrepatas.service;

import app.ws.entrepatas.exception.NoExistEntityException;
import app.ws.entrepatas.model.RazaEntity;
import app.ws.entrepatas.model.TamanoAnimalEntity;

import java.util.List;
import java.util.Optional;

public interface RazaService {
    RazaEntity create(RazaEntity o);
    RazaEntity update(RazaEntity o) throws NoExistEntityException;
    void delete(Long id) throws NoExistEntityException;
    Optional<RazaEntity> findById(Long id);
    List<RazaEntity> findAllById(Long  id);
    List<RazaEntity> findAll();
}
