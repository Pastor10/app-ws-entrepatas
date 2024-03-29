package app.ws.entrepatas.service.impl;

import app.ws.entrepatas.dto.PersonaDto;
import app.ws.entrepatas.enums.ErrorCode;
import app.ws.entrepatas.enums.EstadoAdopcion;
import app.ws.entrepatas.enums.EstadoPublicacion;
import app.ws.entrepatas.exception.ServiceException;
import app.ws.entrepatas.model.AdopcionEntity;
import app.ws.entrepatas.model.PerfilEntity;
import app.ws.entrepatas.model.PersonaEntity;
import app.ws.entrepatas.model.PublicacionEntity;
import app.ws.entrepatas.model.UsuarioEntity;
import app.ws.entrepatas.repository.AdopcionRepository;
import app.ws.entrepatas.repository.PersonaRepository;
import app.ws.entrepatas.repository.PublicacionRepository;
import app.ws.entrepatas.repository.UsuarioRepository;
import app.ws.entrepatas.security.UserPrincipal;
import app.ws.entrepatas.service.AdopcionService;
import app.ws.entrepatas.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class AdopcionServiceImpl implements AdopcionService {


    @Autowired
    AdopcionRepository adopcionRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PersonaRepository personaRepository;

    @Autowired
    PublicacionRepository publicacionRepository;

    @Autowired
    EmailService emailService;

    @Override
    public AdopcionEntity create(AdopcionEntity model, UserPrincipal user) {
        PersonaEntity personaExist = personaRepository.findByNumeroDocumento(model.getPersona().getNumeroDocumento());

        if (personaExist!=null){
            model.setPersona(personaExist);
        }else{
            model.getPersona().setFechaCreacion(LocalDateTime.now());
            model.getPersona().setEliminado(Boolean.FALSE);
            model.getPersona().setNombreCompleto(PersonaDto.getNombresCompletos(model.getPersona()));
            personaRepository.save(model.getPersona());

        }

        if (model.getCreateUser()){
            UsuarioEntity modelExist = usuarioRepository.findByPersonaNumeroDocumento(model.getPersona().getNumeroDocumento());
            if (modelExist!=null){
                throw new ServiceException(ErrorCode.V007);
            }
            UsuarioEntity usuario = new UsuarioEntity();
            usuario.setPersona(model.getPersona());
            usuario.setEliminado(Boolean.FALSE);
            usuario.setEstado(Boolean.TRUE);
            usuario.setFechaCreacion(LocalDateTime.now());
            usuario.setUsuarioCrea(user.getId());
            usuario.setUsername(model.getPersona().getNumeroDocumento());
            usuario.setPassword(new BCryptPasswordEncoder().encode(model.getPersona().getNumeroDocumento()));
            PerfilEntity perfil = new PerfilEntity();
            perfil.setId(2l);
            usuario.setPerfil(perfil);

            usuarioRepository.save(usuario);
        }


        PublicacionEntity publicacion =publicacionRepository.findByAnimal_Id(model.getAnimal().getId());
        publicacion.setFechaModificacion(LocalDateTime.now());
        publicacion.setEstadoPublicacion(EstadoPublicacion.FINALIZADO);
        publicacion.setUsuarioModifica(user.getId());
        publicacionRepository.save(publicacion);

        model.setFechaCreacion(LocalDateTime.now());
        model.setEstadoAdopcion(EstadoAdopcion.RESERVADO);
        model.setEliminado(Boolean.FALSE);
        model.setUsuarioCrea(user.getId());
        emailService.sendEmailAdopcion(model);
        return adopcionRepository.save(model);
    }

    @Override
    public List<AdopcionEntity> findAll() {
        return adopcionRepository.findAll();
    }

    @Override
    public AdopcionEntity update(AdopcionEntity model) {
        AdopcionEntity modelExist = adopcionRepository.findById(model.getId()).orElseThrow(()->new ServiceException(ErrorCode.V002));
        Integer actualAlojado= modelExist.getAnimal().getLocal().getAlojado()==null?0:modelExist.getAnimal().getLocal().getAlojado();
        Integer nuevoAlojado=0;
        if (model.getEstadoAdopcion().equals(EstadoAdopcion.ENTREGADO)){
             nuevoAlojado=actualAlojado-1;

            if (modelExist.getAnimal().getLocal().getCapacidad()> nuevoAlojado){
                modelExist.getAnimal().getLocal().setDisponible(Boolean.TRUE);
            } else{
                modelExist.getAnimal().getLocal().setDisponible(Boolean.FALSE);
            }
            modelExist.getAnimal().getLocal().setAlojado(nuevoAlojado);
            log.info("entregando mascota al local");
        }else if (model.getEstadoAdopcion().equals(EstadoAdopcion.DEVUELTO)){
            nuevoAlojado=actualAlojado+1;

            if (modelExist.getAnimal().getLocal().getCapacidad()> nuevoAlojado){
                modelExist.getAnimal().getLocal().setDisponible(Boolean.TRUE);
            } else{
                modelExist.getAnimal().getLocal().setDisponible(Boolean.FALSE);
            }
            modelExist.getAnimal().getLocal().setAlojado(nuevoAlojado);
            log.info("devolviendo mascota al local");
        }

        modelExist.setFechaModificacion(LocalDateTime.now());
        modelExist.setEstadoAdopcion(model.getEstadoAdopcion());
        modelExist.setFechaEntrega(model.getFechaEntrega());
        modelExist.setFechaDevolucion(model.getFechaDevolucion());
        modelExist.setMotivoDevolucion(model.getMotivoDevolucion());
        //return null;
        return adopcionRepository.save(modelExist);
    }

    @Override
    public Page<AdopcionEntity> findAllDevoluciones(Pageable pageable) {
        List<EstadoAdopcion> estados = Arrays.asList(
                EstadoAdopcion.DEVUELTO
        );
        return adopcionRepository.findAllAdopciones(estados, pageable);
    }

    @Override
    public Page<AdopcionEntity> findAllAdopciones(Pageable pageable) {
        List<EstadoAdopcion> estados = Arrays.asList(
                EstadoAdopcion.RESERVADO,
                EstadoAdopcion.ENTREGADO
        );
        return adopcionRepository.findAllAdopciones(estados, pageable);

    }

    @Override
    public AdopcionEntity findById(Long id) {
        return adopcionRepository.findById(id).orElseThrow(()->new ServiceException(ErrorCode.V002));
    }
}
