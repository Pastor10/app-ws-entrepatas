package app.ws.entrepatas.service;

import app.ws.entrepatas.exception.NoExistEntityException;
import app.ws.entrepatas.model.PublicacionEntity;
import app.ws.entrepatas.security.UserPrincipal;


import java.util.List;
import java.util.Optional;

public interface PublicacionService {
    PublicacionEntity create(PublicacionEntity model, UserPrincipal user);
    List<PublicacionEntity> findAll();
    List<PublicacionEntity> findAllById(Long id);
    PublicacionEntity update(PublicacionEntity model, UserPrincipal user);
    PublicacionEntity findById(Long id);
    PublicacionEntity findByIdAnimal(Long id);

}
