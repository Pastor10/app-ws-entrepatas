package app.ws.entrepatas.service;

import app.ws.entrepatas.exception.NoExistEntityException;
import app.ws.entrepatas.model.VeterinarioEntity;
import app.ws.entrepatas.security.UserPrincipal;

import java.util.List;
import java.util.Optional;

public interface VeterinarioService {

    List<VeterinarioEntity> findAll();
    VeterinarioEntity create(VeterinarioEntity model, UserPrincipal user);
    void delete(Long id, UserPrincipal user);
    VeterinarioEntity update(VeterinarioEntity model, UserPrincipal user);
    VeterinarioEntity findById(Long id);
}
