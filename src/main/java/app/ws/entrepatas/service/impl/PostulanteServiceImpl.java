package app.ws.entrepatas.service.impl;

import app.ws.entrepatas.enums.ErrorCode;
import app.ws.entrepatas.exception.NoExistEntityException;
import app.ws.entrepatas.exception.ServiceException;
import app.ws.entrepatas.model.DetalleCuestionarioEntity;
import app.ws.entrepatas.model.OpcionEntity;
import app.ws.entrepatas.model.PersonaEntity;
import app.ws.entrepatas.model.PostulanteColaboradorEntity;
import app.ws.entrepatas.model.PostulanteEntity;
import app.ws.entrepatas.model.PublicacionEntity;
import app.ws.entrepatas.model.TipoCuestionarioEntity;
import app.ws.entrepatas.model.UsuarioEntity;
import app.ws.entrepatas.repository.CuestionarioRepository;
import app.ws.entrepatas.repository.OpcionRepository;
import app.ws.entrepatas.repository.PersonaRepository;
import app.ws.entrepatas.repository.PostulanteColaboradorRepository;
import app.ws.entrepatas.repository.PostulanteRepository;
import app.ws.entrepatas.security.UserPrincipal;
import app.ws.entrepatas.service.EmailService;
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

    @Autowired
    PostulanteColaboradorRepository postulanteColaboradorRepository;

    @Autowired
    EmailService emailService;

    @Autowired
    CuestionarioRepository cuestionarioRepository;

    @Autowired
    OpcionRepository opcionRepository;

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
        emailService.sendEmailPostulante(model);
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

    @Override
    public PostulanteColaboradorEntity createSolicitud(PostulanteColaboradorEntity model) {
        PersonaEntity persona = personaRepository.findByNumeroDocumento(model.getPersona().getNumeroDocumento());
        if (persona==null){
            model.getPersona().setEliminado(Boolean.FALSE);
            model.getPersona().setFechaCreacion(LocalDateTime.now());
            personaRepository.save(model.getPersona());
        }else{
            PostulanteColaboradorEntity modelExist = postulanteColaboradorRepository.findByPersona_Id(persona.getId());
            if (modelExist!=null){
                throw new ServiceException(ErrorCode.V008);
            }
            model.setPersona(persona);

        }

        for (DetalleCuestionarioEntity item: model.getCuestionario().getListaDetalle()) {
            item.setCuestionario(model.getCuestionario());
            // OpcionEntity opcion = opcionRepository.findById(item.getOpcion().getId()).orElseThrow(()-> new ServiceException(ErrorCode.V002));
            // item.setOpcion(opcion);
        }
        model.getCuestionario().setFechaCreacion(LocalDateTime.now());
        model.getCuestionario().setEliminado(Boolean.FALSE);
        model.getCuestionario().setTipoCuestionario(TipoCuestionarioEntity.builder().id(2l).build());
        cuestionarioRepository.save(model.getCuestionario());

        model.setFechaCreacion(LocalDateTime.now());
        model.setEliminado(Boolean.FALSE);
        return postulanteColaboradorRepository.save(model);
    }
}
