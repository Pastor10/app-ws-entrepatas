package app.ws.entrepatas.service;

import app.ws.entrepatas.model.UbigeoEntity;

import java.util.List;

public interface UbigeoService {

    List<UbigeoEntity> searchCity(String filter);
    UbigeoEntity getCity(String codDep, String codProv, String codDist);
}
