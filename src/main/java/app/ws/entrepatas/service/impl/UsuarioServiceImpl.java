package app.ws.entrepatas.service.impl;

import app.ws.entrepatas.dto.PersonaDto;
import app.ws.entrepatas.enums.ErrorCode;
import app.ws.entrepatas.exception.ServiceException;
import app.ws.entrepatas.model.PerfilEntity;
import app.ws.entrepatas.model.PersonaEntity;
import app.ws.entrepatas.model.UsuarioEntity;
import app.ws.entrepatas.repository.PerfilRepository;
import app.ws.entrepatas.repository.PersonaRepository;
import app.ws.entrepatas.repository.UsuarioRepository;
import app.ws.entrepatas.security.UserPrincipal;
import app.ws.entrepatas.service.EmailService;
import app.ws.entrepatas.service.UsuarioService;
import app.ws.entrepatas.util.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private EmailService emailService;

    @Override
    public UsuarioEntity create(UsuarioEntity model, UserPrincipal user) {

        UsuarioEntity modelExist = repository.findByPersonaNumeroDocumento(model.getPersona().getNumeroDocumento());

        if (modelExist!=null){
            throw new ServiceException(ErrorCode.V007);
        }
        PersonaEntity personaExist = personaRepository.findByNumeroDocumento(model.getPersona().getNumeroDocumento());
        if (personaExist!=null){
            model.setPersona(personaExist);
        }

        model.getPersona().setFechaCreacion(LocalDateTime.now());
        model.getPersona().setEliminado(Boolean.FALSE);
        model.getPersona().setNombreCompleto(PersonaDto.getNombresCompletos(model.getPersona()));
        personaRepository.save(model.getPersona());

        model.setEliminado(Boolean.FALSE);
        model.setEstado(Boolean.TRUE);
        model.setPassword(new BCryptPasswordEncoder().encode(model.getPersona().getNumeroDocumento()));
        model.setUsername(model.getPersona().getNumeroDocumento());
        model.setUsuarioCrea(user.getId());
        model.setFechaCreacion(LocalDateTime.now());
        return repository.save(model);
    }

    @Override
    public UsuarioEntity createVisitante(UsuarioEntity model) {
        PerfilEntity perfil = perfilRepository.findByNombreIsContainingIgnoreCase(Constantes.PERFIL_VISITANTE);
        model.setPerfil(perfil);
        model.setEstado(Boolean.FALSE);
        model.setEliminado(Boolean.FALSE);
        model.setFechaCreacion(LocalDateTime.now());
        model.setPassword(new BCryptPasswordEncoder().encode(model.getPassword()));
        model.setUsername(model.getPersona().getNumeroDocumento());
        model.setUuid(UUID.randomUUID().toString());

        UsuarioEntity modelExist = repository.findByPersonaNumeroDocumento(model.getPersona().getNumeroDocumento());

        if (modelExist!=null){
            throw new ServiceException(ErrorCode.V007);
        }
        PersonaEntity personaExist = personaRepository.findByNumeroDocumento(model.getPersona().getNumeroDocumento());
        if (personaExist!=null){
            model.setPersona(personaExist);
        }
        model.getPersona().setFechaCreacion(LocalDateTime.now());
        model.getPersona().setEliminado(Boolean.FALSE);
        personaRepository.save(model.getPersona());
        emailService.sendEmailActiveAccount(model);
        return repository.save(model);
    }

    @Override
    public UsuarioEntity update(UsuarioEntity model, UserPrincipal user) {
        UsuarioEntity modelExist = findById(model.getId());
        modelExist.getPersona().setTipoDocumento(model.getPersona().getTipoDocumento());
        modelExist.getPersona().setNumeroDocumento(model.getPersona().getNumeroDocumento());
        modelExist.getPersona().setNombre(model.getPersona().getNombre());
        modelExist.getPersona().setApePaterno(model.getPersona().getApePaterno());
        modelExist.getPersona().setApeMaterno(model.getPersona().getApeMaterno());
        modelExist.getPersona().setCelular(model.getPersona().getCelular());
        modelExist.getPersona().setCorreo(model.getPersona().getCorreo());
        modelExist.getPersona().setNombreCompleto(PersonaDto.getNombresCompletos(model.getPersona()));
        modelExist.getPersona().setFechaModificacion(LocalDateTime.now());
        modelExist.setPassword(new BCryptPasswordEncoder().encode(model.getPersona().getNumeroDocumento()));
        modelExist.setPerfil(model.getPerfil());
        modelExist.setEstado(model.getEstado());
        modelExist.setFechaModificacion(LocalDateTime.now());
        modelExist.setUsuarioModifica(user.getId());
        return repository.save(modelExist);

    }

    @Override
    public void delete(Long id, UserPrincipal user) {
        repository.delete(id, user.getId(), LocalDateTime.now());

    }

    @Override
    public UsuarioEntity findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ServiceException(ErrorCode.V002.getCode(), ErrorCode.V002.getMessage()));
        
    }

    @Override
    public Page<UsuarioEntity> findAll(Pageable pageable) {
    	return repository.findAllUsers(pageable);
    }

    @Override
    public List<UsuarioEntity> findAllIntegrantes() {
        return repository.findAll();
    }

    @Override
    public Boolean validateUuid(String uuid) throws ServiceException {
        UsuarioEntity user = repository.findByUuid(uuid).orElseThrow(()->new ServiceException(ErrorCode.V002));
        user.setEstado(Boolean.TRUE);
        user.setFechaModificacion(LocalDateTime.now());
        repository.save(user);
        return Boolean.TRUE;
    }
}
