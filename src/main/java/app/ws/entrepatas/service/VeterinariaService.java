package app.ws.entrepatas.service;

import app.ws.entrepatas.exception.NoExistEntityException;
import app.ws.entrepatas.model.VeterinariaEntity;
import app.ws.entrepatas.security.UserPrincipal;

import java.util.List;

public interface VeterinariaService {
    List<VeterinariaEntity> findAll();
    VeterinariaEntity create(VeterinariaEntity model, UserPrincipal user);
    void delete(Long id, UserPrincipal user);
    VeterinariaEntity update(VeterinariaEntity model, UserPrincipal user);
    VeterinariaEntity findById(Long id);
}
