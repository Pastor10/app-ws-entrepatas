package app.ws.entrepatas.service;

import app.ws.entrepatas.model.AdopcionEntity;
import app.ws.entrepatas.security.UserPrincipal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdopcionService {
    AdopcionEntity create(AdopcionEntity model, UserPrincipal user);
    List<AdopcionEntity> findAll();
    Page<AdopcionEntity> findAllDevoluciones(Pageable pageable);
    Page<AdopcionEntity> findAllAdopciones(Pageable pageable);
    AdopcionEntity update(AdopcionEntity model);
    AdopcionEntity findById(Long id);
}
