package app.ws.entrepatas.service;

import app.ws.entrepatas.exception.NoExistEntityException;
import app.ws.entrepatas.model.PostulanteEntity;
import app.ws.entrepatas.security.UserPrincipal;

import java.util.List;
import java.util.Optional;

public interface PostulanteService {

    PostulanteEntity create(PostulanteEntity model);
    List<PostulanteEntity> findAllByPublicacion(Long idPublicacion);
    PostulanteEntity update(PostulanteEntity model, UserPrincipal user);
    PostulanteEntity findById(Long id);
}
