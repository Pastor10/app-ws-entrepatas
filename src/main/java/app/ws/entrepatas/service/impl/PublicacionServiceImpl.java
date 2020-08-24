package app.ws.entrepatas.service.impl;

import app.ws.entrepatas.enums.CondicionAnimal;
import app.ws.entrepatas.enums.ErrorCode;
import app.ws.entrepatas.enums.EstadoPublicacion;
import app.ws.entrepatas.exception.NoExistEntityException;
import app.ws.entrepatas.exception.ServiceException;
import app.ws.entrepatas.model.AnimalEntity;
import app.ws.entrepatas.model.PublicacionEntity;
import app.ws.entrepatas.repository.AnimalRepository;
import app.ws.entrepatas.repository.PublicacionRepository;
import app.ws.entrepatas.security.UserPrincipal;
import app.ws.entrepatas.service.PublicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PublicacionServiceImpl implements PublicacionService {

    @Autowired
    PublicacionRepository publicacionRepository;

    @Autowired
    AnimalRepository animalRepository;



    @Override
    public PublicacionEntity create(PublicacionEntity model, UserPrincipal user) {

        model.getAnimal().setFechaCreacion(LocalDateTime.now());
        model.getAnimal().setDisponible(Boolean.FALSE);
        model.getAnimal().setNombre(model.getAnimal().getNombre().toUpperCase());
        animalRepository.save(model.getAnimal());

        //animalRepository.save(animal);
        model.setAnimal(model.getAnimal());
        model.setEstadoPublicacion(EstadoPublicacion.PENDIENTE);
        model.setEliminado(Boolean.FALSE);
        model.setUsuarioCrea(user.getId());
        model.setFechaCreacion(LocalDateTime.now());
        return publicacionRepository.save(model);
    }

    @Override
    public Page<PublicacionEntity> findAll(LocalDate desde, LocalDate hasta, List<CondicionAnimal> condicion, Pageable page) {
        Long idPerfil = 2l;
        return publicacionRepository.findAllPublicaciones(desde, hasta, condicion,idPerfil,page);
    }

    @Override
    public Page<PublicacionEntity> findAllVisitantes(Pageable page) {
        Long idPerfil = 2l;
        return publicacionRepository.findAllPublicacionesVisitantes(idPerfil,page);
    }

    @Override
    public List<PublicacionEntity> findAllById(Long id) {
        return publicacionRepository.findAllByUsuarioPublica_Id(id);
    }

    @Override
    public PublicacionEntity update(PublicacionEntity model, UserPrincipal user){
        PublicacionEntity modelExist = findById(model.getId());

        modelExist.setEstadoPublicacion(model.getEstadoPublicacion());
        modelExist.getAnimal().setTamanoAnimal(model.getAnimal().getTamanoAnimal());
        modelExist.getAnimal().setRaza(model.getAnimal().getRaza());
        modelExist.getAnimal().setSexo(model.getAnimal().getSexo());
        modelExist.setCondicion(model.getCondicion());
        modelExist.getAnimal().setEdad(model.getAnimal().getEdad());
        modelExist.getAnimal().setLocal(model.getAnimal().getLocal());
        modelExist.getAnimal().setNombre(model.getAnimal().getNombre());
        modelExist.getAnimal().setFoto(model.getAnimal().getFoto());
        modelExist.setDescripcion(model.getDescripcion());
        modelExist.setObservacion(model.getObservacion());
        modelExist.setUsuarioModifica(user.getId());
        modelExist.setFechaModificacion(LocalDateTime.now());


        return publicacionRepository.save(modelExist);

    }

    @Override
    public PublicacionEntity findById(Long id) {
        return publicacionRepository.findById(id).orElseThrow(()-> new ServiceException(ErrorCode.V002));
    }

    @Override
    public PublicacionEntity findByIdAnimal(Long id) {
        return publicacionRepository.findByAnimal_Id(id);
    }

    @Override
    public List<PublicacionEntity> findAllPublicaciones() {
        return publicacionRepository.findAll();
    }


}
