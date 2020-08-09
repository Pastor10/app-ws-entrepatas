package app.ws.entrepatas.service;

import app.ws.entrepatas.model.AnimalEntity;
import app.ws.entrepatas.model.PublicacionEntity;

import java.util.List;
import java.util.Optional;

public interface AnimalService {

    List<AnimalEntity> findAll();
    Optional<AnimalEntity>findById(Long id);

}
