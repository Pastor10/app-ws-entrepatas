package app.ws.entrepatas.service.impl;

import app.ws.entrepatas.dto.PersonaDto;
import app.ws.entrepatas.enums.ErrorCode;
import app.ws.entrepatas.enums.EstadoAdopcion;
import app.ws.entrepatas.exception.ServiceException;
import app.ws.entrepatas.model.AdopcionEntity;
import app.ws.entrepatas.model.PersonaEntity;
import app.ws.entrepatas.model.UsuarioEntity;
import app.ws.entrepatas.repository.AdopcionRepository;
import app.ws.entrepatas.repository.PersonaRepository;
import app.ws.entrepatas.repository.UsuarioRepository;
import app.ws.entrepatas.service.AdopcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class AdopcionServiceImpl implements AdopcionService {


    @Autowired
    AdopcionRepository adopcionRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PersonaRepository personaRepository;

    @Override
    public AdopcionEntity create(AdopcionEntity model) {
        PersonaEntity personaExist = personaRepository.findByNumeroDocumento(model.getPersona().getNumeroDocumento());

        if (personaExist!=null){
            model.setPersona(personaExist);
        }else{
            model.getPersona().setFechaCreacion(LocalDateTime.now());
            model.getPersona().setEliminado(Boolean.FALSE);
            model.getPersona().setNombreCompleto(PersonaDto.getNombresCompletos(model.getPersona()));
            personaRepository.save(model.getPersona());

            if (model.getCreateUser()){
                UsuarioEntity user = new UsuarioEntity();
                user.setPersona(model.getPersona());
                user.setEliminado(Boolean.FALSE);
                user.setFechaCreacion(LocalDateTime.now());
                user.setUsername(model.getPersona().getCorreo());
                user.setPassword(new BCryptPasswordEncoder().encode(model.getPersona().getNumeroDocumento()));
                usuarioRepository.save(user);
            }
        }

        model.setFechaCreacion(LocalDateTime.now());
        model.setEstadoAdopcion(EstadoAdopcion.RESERVADO);
        model.setEliminado(Boolean.FALSE);
        return adopcionRepository.save(model);
    }

    @Override
    public List<AdopcionEntity> findAll() {
        return adopcionRepository.findAll();
    }

    @Override
    public AdopcionEntity update(AdopcionEntity model) {
        AdopcionEntity modelExist = adopcionRepository.findById(model.getId()).orElseThrow(()->new ServiceException(ErrorCode.V002));
        modelExist.setFechaModificacion(LocalDateTime.now());
        return adopcionRepository.save(modelExist);
    }

    @Override
    public List<AdopcionEntity> findAllDevoluciones() {
        List<EstadoAdopcion> estados = Arrays.asList(
                EstadoAdopcion.DEVUELTO
        );
        return adopcionRepository.findAllAdopciones(estados);
    }

    @Override
    public List<AdopcionEntity> findAllAdopciones() {
        List<EstadoAdopcion> estados = Arrays.asList(
                EstadoAdopcion.RESERVADO,
                EstadoAdopcion.ENTREGADO
        );
        return adopcionRepository.findAllAdopciones(estados);

    }

    @Override
    public AdopcionEntity findById(Long id) {
        return adopcionRepository.findById(id).orElseThrow(()->new ServiceException(ErrorCode.V002));
    }
}
