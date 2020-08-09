package app.ws.entrepatas.service.impl;

import app.ws.entrepatas.model.TipoDocumentoEntity;
import app.ws.entrepatas.repository.TipoDocumentoRepository;
import app.ws.entrepatas.service.TipoDocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TipoDocumentoServiceIml implements TipoDocumentoService {
    @Autowired
    TipoDocumentoRepository tipoDocumentoRepository;

    @Override
    public List<TipoDocumentoEntity> findAll() {
        return tipoDocumentoRepository.findAll();
    }
}
