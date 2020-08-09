package app.ws.entrepatas.service;

import app.ws.entrepatas.model.AnimalEntity;
import app.ws.entrepatas.model.PersonaEntity;

import java.util.Optional;

public interface PersonaService {
    PersonaEntity findByDocumento(String documento);
    PersonaEntity update(PersonaEntity persona);
    PersonaEntity findById(Long id);

}
