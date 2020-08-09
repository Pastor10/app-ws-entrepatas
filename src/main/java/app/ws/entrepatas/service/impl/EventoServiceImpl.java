package app.ws.entrepatas.service.impl;

import app.ws.entrepatas.model.EventoEntity;
import app.ws.entrepatas.repository.EventoRepository;
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
    public EventoEntity create(EventoEntity o) {
        o.setId(null);
        o.setFechaCreacion(LocalDateTime.now());
        EventoEntity evento = eventoRepository.save(o);
        return evento;
    }

    @Override
    public List<EventoEntity> findAll() {
        return eventoRepository.findAll();
    }
}
