package app.ws.entrepatas.service.impl;

import app.ws.entrepatas.enums.ErrorCode;
import app.ws.entrepatas.exception.ServiceException;
import app.ws.entrepatas.model.PersonaEntity;
import app.ws.entrepatas.repository.PersonaRepository;
import app.ws.entrepatas.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    PersonaRepository personaRepository;

    @Override
    public PersonaEntity findByDocumento(String documento) {
        PersonaEntity modelExist =personaRepository.findByNumeroDocumento(documento);
        if (modelExist==null){
            throw new ServiceException(ErrorCode.V002);
        }
        return modelExist;
    }

    @Override
    public PersonaEntity update(PersonaEntity persona) {
        PersonaEntity modelExist = findById(persona.getId());
        modelExist.setTipoDocumento(persona.getTipoDocumento());
        modelExist.setNumeroDocumento(persona.getNumeroDocumento());
        modelExist.setNombre(persona.getNombre());
        modelExist.setApePaterno(persona.getApePaterno());
        modelExist.setApeMaterno(persona.getApeMaterno());
        modelExist.setCelular(persona.getCelular());
        modelExist.setCorreo(persona.getCorreo());
        modelExist.setDireccion(persona.getDireccion());
        modelExist.setUbigeo(persona.getUbigeo());
        modelExist.setOcupacion(persona.getOcupacion());
        modelExist.setFechaModificacion(LocalDateTime.now());
        modelExist.setUbigeo(persona.getUbigeo());
        modelExist.setIsCompleted(Boolean.TRUE);
        modelExist.setFoto(persona.getFoto());
        return personaRepository.save(modelExist);
    }

    @Override
    public PersonaEntity findById(Long id) {
        return personaRepository.findById(id).orElseThrow(()-> new ServiceException(ErrorCode.V002));
    }
}
