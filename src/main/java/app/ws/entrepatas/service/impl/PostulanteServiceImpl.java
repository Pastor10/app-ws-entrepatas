package app.ws.entrepatas.service.impl;

import app.ws.entrepatas.enums.ErrorCode;
import app.ws.entrepatas.exception.NoExistEntityException;
import app.ws.entrepatas.exception.ServiceException;
import app.ws.entrepatas.model.PersonaEntity;
import app.ws.entrepatas.model.PostulanteEntity;
import app.ws.entrepatas.model.PublicacionEntity;
import app.ws.entrepatas.model.UsuarioEntity;
import app.ws.entrepatas.repository.PersonaRepository;
import app.ws.entrepatas.repository.PostulanteRepository;
import app.ws.entrepatas.security.UserPrincipal;
import app.ws.entrepatas.service.PostulanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PostulanteServiceImpl implements PostulanteService {

    @Autowired
    PostulanteRepository postulanteRepository;

    @Autowired
    PersonaRepository personaRepository;

    @Override
    public PostulanteEntity create(PostulanteEntity model) {

        // buscar persona
        PersonaEntity persona = personaRepository.findByNumeroDocumento(model.getPersona().getNumeroDocumento());
        if(persona==null){
            model.getPersona().setEliminado(Boolean.FALSE);
            model.getPersona().setFechaCreacion(LocalDateTime.now());
            personaRepository.save(model.getPersona());
        }else{

            persona.setUbigeo(model.getPersona().getUbigeo());
            persona.setDireccion(model.getPersona().getDireccion());
            persona.setCelular(model.getPersona().getCelular());
            persona.setFechaModificacion(LocalDateTime.now());
            persona.setIsCompleted(Boolean.TRUE);
            personaRepository.save(persona);
            model.setPersona(persona);
            List<PostulanteEntity> listaPostulaciones = postulanteRepository.findAllByPersonaId(persona.getId());
            listaPostulaciones.forEach(item ->{
                if (item.getPublicacion().getId().equals(model.getPublicacion().getId())){
                    throw new ServiceException(ErrorCode.V006);
                }
            });

        }
        model.setEliminado(Boolean.FALSE);
        model.setFechaCreacion(LocalDateTime.now());
        return postulanteRepository.save(model);
    }

    @Override
    public List<PostulanteEntity> findAllByPublicacion(Long idPublicacion) {
        return postulanteRepository.findAllByPublicacionId(idPublicacion);
    }

    @Override
    public PostulanteEntity update(PostulanteEntity model, UserPrincipal user)  {
        PostulanteEntity modelExist = findById(model.getId());
        modelExist.setPuntuacion(model.getPuntuacion());
        modelExist.setFechaModificacion(LocalDateTime.now());
        modelExist.setUsuarioModifica(user.getId());

        return postulanteRepository.save(modelExist);

    }

    @Override
    public PostulanteEntity findById(Long id) {
        return  postulanteRepository.findById(id).orElseThrow(()->new ServiceException(ErrorCode.V002));

    }
}
