package app.ws.entrepatas.service;

import app.ws.entrepatas.model.TipoDocumentoEntity;

import java.util.List;

public interface TipoDocumentoService {
    List<TipoDocumentoEntity> findAll();
}
