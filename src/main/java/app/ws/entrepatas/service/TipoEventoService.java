package app.ws.entrepatas.service;

import app.ws.entrepatas.model.TipoEventoEntity;
import app.ws.entrepatas.model.TipoLocalEntity;

import java.util.List;

public interface TipoEventoService {
    TipoEventoEntity create(TipoEventoEntity o);
    List<TipoEventoEntity> findAll();
}
