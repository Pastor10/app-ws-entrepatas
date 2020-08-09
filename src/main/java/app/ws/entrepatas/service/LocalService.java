package app.ws.entrepatas.service;

import app.ws.entrepatas.model.LocalEntity;
import app.ws.entrepatas.security.UserPrincipal;

import java.util.List;

public interface LocalService {

    List<LocalEntity> findAll();
    LocalEntity create(LocalEntity model, UserPrincipal user);
    LocalEntity update(LocalEntity model, UserPrincipal user);
    LocalEntity findById(Long id);
}
