package app.ws.entrepatas.service;

import app.ws.entrepatas.model.EventoEntity;

import java.util.List;

public interface EventoService {
    EventoEntity create(EventoEntity o);
    List<EventoEntity> findAll();
}
