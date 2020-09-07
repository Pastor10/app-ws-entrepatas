package app.ws.entrepatas.service.impl;

import app.ws.entrepatas.enums.ErrorCode;
import app.ws.entrepatas.exception.ServiceException;
import app.ws.entrepatas.model.EventoEntity;
import app.ws.entrepatas.repository.EventoRepository;
import app.ws.entrepatas.security.UserPrincipal;
import app.ws.entrepatas.service.EventoService;
import app.ws.entrepatas.util.UtilDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventoServiceImpl implements EventoService {

    @Autowired
    EventoRepository eventoRepository;

    @Override
    public EventoEntity create(EventoEntity model, UserPrincipal user) {
        LocalDateTime fecha = UtilDate.stringToLocalDateTime(model.getFechaEvento(),"yyyy-MM-dd HH:mm");
        model.setFechaCreacion(LocalDateTime.now());
        model.setUsuarioCrea(user.getId());
        model.setEliminado(Boolean.FALSE);
        model.setFecha(fecha);
        return eventoRepository.save(model);
    }

    @Override
    public Page<EventoEntity> findAll(Pageable pageable) {
        return eventoRepository.findAllEvento(pageable);
    }

    @Override
    public EventoEntity findById(Long id) {
        return eventoRepository.findById(id).orElseThrow(()-> new ServiceException(ErrorCode.V002));
    }

    @Override
    public EventoEntity update(EventoEntity model, UserPrincipal user) {
        EventoEntity modelExist= findById(model.getId());
        LocalDateTime fecha = UtilDate.stringToLocalDateTime(model.getFechaEvento(),"yyyy-MM-dd HH:mm");
        modelExist.setTipoEvento(model.getTipoEvento());
        modelExist.setTitulo(model.getTitulo());
        modelExist.setBanner(model.getBanner());
        modelExist.setDescripcion(model.getDescripcion());
        modelExist.setFecha(fecha);
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

    @Override
    public List<EventoEntity> findAllProximos() {
        return eventoRepository.findAll();
    }
}
