package app.ws.entrepatas.service;

import app.ws.entrepatas.model.CitaMedicaEntity;
import app.ws.entrepatas.model.EventoEntity;
import app.ws.entrepatas.security.UserPrincipal;

import java.util.List;

public interface CitaMedicaService {
    CitaMedicaEntity create(CitaMedicaEntity model);
    List<CitaMedicaEntity> findAll();
    Integer getCitaUltimaByIdAnimal(Long id);
    void delete(Long id, UserPrincipal user);
}
