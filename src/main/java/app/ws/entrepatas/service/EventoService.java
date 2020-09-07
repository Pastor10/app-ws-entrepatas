package app.ws.entrepatas.service;

import app.ws.entrepatas.model.EventoEntity;
import app.ws.entrepatas.security.UserPrincipal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EventoService {
    EventoEntity create(EventoEntity model, UserPrincipal user);
    Page<EventoEntity> findAll(Pageable pageable);
    List<EventoEntity> findAllProximos();
    EventoEntity findById(Long id);
    EventoEntity update(EventoEntity model, UserPrincipal user);
    void delete(Long id, UserPrincipal user);
}
