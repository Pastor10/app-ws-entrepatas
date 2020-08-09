package app.ws.entrepatas.service;

import app.ws.entrepatas.model.AdopcionEntity;

import java.util.List;

public interface AdopcionService {
    AdopcionEntity create(AdopcionEntity model);
    List<AdopcionEntity> findAll();
    List<AdopcionEntity> findAllDevoluciones();
    List<AdopcionEntity> findAllAdopciones();
    AdopcionEntity update(AdopcionEntity model);
    AdopcionEntity findById(Long id);
}
