package app.ws.entrepatas.service.impl;

import app.ws.entrepatas.model.TipoEventoEntity;
import app.ws.entrepatas.repository.TipoEventoRepository;
import app.ws.entrepatas.service.TipoEventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TipoEventoServiceImpl implements TipoEventoService {

    @Autowired
    TipoEventoRepository tipoEventoRepository;

    @Override
    public TipoEventoEntity create(TipoEventoEntity o) {
        o.setFechaCreacion(LocalDateTime.now());
        return tipoEventoRepository.save(o);
    }

    @Override
    public List<TipoEventoEntity> findAll() {
        return tipoEventoRepository.findAll();
    }
}
