package app.ws.entrepatas.service.impl;

import app.ws.entrepatas.enums.ErrorCode;
import app.ws.entrepatas.exception.ServiceException;
import app.ws.entrepatas.model.EventoEntity;
import app.ws.entrepatas.repository.EventoRepository;
import app.ws.entrepatas.security.UserPrincipal;
import app.ws.entrepatas.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventoServiceImpl implements EventoService {

    @Autowired
    EventoRepository eventoRepository;

    @Override
    public EventoEntity create(EventoEntity model, UserPrincipal user) {
        model.setFechaCreacion(LocalDateTime.now());
        model.setUsuarioCrea(user.getId());
        model.setEliminado(Boolean.FALSE);
        return eventoRepository.save(model);
    }

    @Override
    public List<EventoEntity> findAll() {
        return eventoRepository.findAll();
    }

    @Override
    public EventoEntity findById(Long id) {
        return eventoRepository.findById(id).orElseThrow(()-> new ServiceException(ErrorCode.V002));
    }

    @Override
    public EventoEntity update(EventoEntity model, UserPrincipal user) {
        EventoEntity modelExist= findById(model.getId());
        modelExist.setTipoEvento(model.getTipoEvento());
        modelExist.setTitulo(model.getTitulo());
        modelExist.setBanner(model.getBanner());
        modelExist.setDescripcion(model.getDescripcion());
        modelExist.setFecha(model.getFecha());
        modelExist.setUbigeo(model.getUbigeo());
        modelExist.setDireccion(model.getDireccion());
        modelExist.setFechaModificacion(LocalDateTime.now());
        modelExist.setUsuarioModifica(user.getId());
        return eventoRepository.save(modelExist);
    }

    @Override
    public void delete(Long id, UserPrincipal user) {
        eventoRepository.delete(id, user.getId(), LocalDateTime.now());
    }
}
