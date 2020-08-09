package app.ws.entrepatas.service.impl;

import app.ws.entrepatas.model.EstadoCivilEntity;
import app.ws.entrepatas.repository.EstadoCivilRepository;
import app.ws.entrepatas.service.EstadoCivilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoCivilServiceImpl implements EstadoCivilService {

    @Autowired
    EstadoCivilRepository estadoCivilRepository;

    @Override
    public List<EstadoCivilEntity> findAll() {
        return estadoCivilRepository.findAll();
    }
}
