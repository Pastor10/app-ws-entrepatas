package app.ws.entrepatas.service;

import app.ws.entrepatas.exception.NoExistEntityException;
import app.ws.entrepatas.model.TipoEventoEntity;
import app.ws.entrepatas.model.TipoLocalEntity;

import java.util.List;

public interface TipoEventoService {
    TipoEventoEntity create(TipoEventoEntity model);
    List<TipoEventoEntity> findAll();
    void delete(Long id) throws NoExistEntityException;
}
