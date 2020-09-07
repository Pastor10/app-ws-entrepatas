package app.ws.entrepatas.service;

import app.ws.entrepatas.exception.NoExistEntityException;
import app.ws.entrepatas.model.TipoEventoEntity;
import app.ws.entrepatas.model.TipoLocalEntity;
import app.ws.entrepatas.security.UserPrincipal;

import java.util.List;

public interface TipoEventoService {
    TipoEventoEntity create(TipoEventoEntity model);
    List<TipoEventoEntity> findAll();
    void delete(Long id) throws NoExistEntityException;
    TipoEventoEntity update(TipoEventoEntity model, UserPrincipal user);
    TipoEventoEntity findById(Long id);
}
