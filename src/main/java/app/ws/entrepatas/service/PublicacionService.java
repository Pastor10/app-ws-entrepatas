package app.ws.entrepatas.service;

import app.ws.entrepatas.enums.CondicionAnimal;
import app.ws.entrepatas.enums.EstadoPublicacion;
import app.ws.entrepatas.exception.NoExistEntityException;
import app.ws.entrepatas.model.PublicacionEntity;
import app.ws.entrepatas.security.UserPrincipal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PublicacionService {
    PublicacionEntity create(PublicacionEntity model, UserPrincipal user);
    Page<PublicacionEntity> findAll(LocalDate desde, LocalDate hasta, List<CondicionAnimal> condicion, Pageable page);
    Page<PublicacionEntity> findAllVisitantes(Pageable page);
    List<PublicacionEntity> findAllPublicaciones();
    List<PublicacionEntity> findAllById(Long id);
    PublicacionEntity update(PublicacionEntity model, UserPrincipal user);
    PublicacionEntity findById(Long id);
    PublicacionEntity findByIdAnimal(Long id);

}
