package app.ws.entrepatas.service;

import app.ws.entrepatas.model.CitaMedicaEntity;
import app.ws.entrepatas.model.EventoEntity;

import java.util.List;

public interface CitaMedicaService {
    CitaMedicaEntity create(CitaMedicaEntity model);
    List<CitaMedicaEntity> findAll();
    Integer getCitaUltimaByIdAnimal(Long id);
}
