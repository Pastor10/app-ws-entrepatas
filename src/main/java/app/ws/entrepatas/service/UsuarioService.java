package app.ws.entrepatas.service;


import app.ws.entrepatas.exception.NoExistEntityException;
import app.ws.entrepatas.model.UsuarioEntity;
import app.ws.entrepatas.security.UserPrincipal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    UsuarioEntity create(UsuarioEntity model, UserPrincipal user);
    UsuarioEntity createVisitante(UsuarioEntity model);
    UsuarioEntity update(UsuarioEntity model, UserPrincipal user);
    void delete(Long id, UserPrincipal user);
    UsuarioEntity findById(Long id);
    Page<UsuarioEntity> findAll(Pageable pageable);
    List<UsuarioEntity> findAllIntegrantes();
}
