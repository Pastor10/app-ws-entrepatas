package app.ws.entrepatas.service;

import app.ws.entrepatas.model.EventoEntity;
import app.ws.entrepatas.security.UserPrincipal;

import java.util.List;

public interface EventoService {
    EventoEntity create(EventoEntity model, UserPrincipal user);
    List<EventoEntity> findAll();
    EventoEntity findById(Long id);
    EventoEntity update(EventoEntity model, UserPrincipal user);
    void delete(Long id, UserPrincipal user);
}
