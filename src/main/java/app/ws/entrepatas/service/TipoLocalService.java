package app.ws.entrepatas.service;

import app.ws.entrepatas.exception.NoExistEntityException;
import app.ws.entrepatas.model.TipoLocalEntity;

import java.util.List;

public interface TipoLocalService {

    List<TipoLocalEntity> findAll();
    TipoLocalEntity create(TipoLocalEntity model);
    TipoLocalEntity update(TipoLocalEntity model) ;
    void delete(Long id) throws NoExistEntityException;
    TipoLocalEntity findById(Long id);
}
