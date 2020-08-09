package app.ws.entrepatas.service.impl;

import app.ws.entrepatas.enums.ErrorCode;
import app.ws.entrepatas.exception.NoExistEntityException;
import app.ws.entrepatas.exception.ServiceException;
import app.ws.entrepatas.model.VeterinariaEntity;
import app.ws.entrepatas.repository.VeterinariaRepository;
import app.ws.entrepatas.security.UserPrincipal;
import app.ws.entrepatas.service.VeterinariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class VeterinariaServiceImpl implements VeterinariaService {

    @Autowired
    VeterinariaRepository veterinariaRepository;

    @Override
    public List<VeterinariaEntity> findAll() {
        return veterinariaRepository.findAll();
    }

    @Override
    public VeterinariaEntity create(VeterinariaEntity model, UserPrincipal user) {
        model.setFechaCreacion(LocalDateTime.now());
        model.setUsuarioCrea(user.getId());
        return veterinariaRepository.save(model);
    }

    @Override
    public void delete(Long id, UserPrincipal user){
       veterinariaRepository.delete(id, user.getId(), LocalDateTime.now());
    }

    @Override
    public VeterinariaEntity update(VeterinariaEntity model, UserPrincipal user) {
        VeterinariaEntity modelExist = findById(model.getId());
        modelExist.setFechaModificacion(LocalDateTime.now());
        modelExist.setDireccion(model.getDireccion());
        modelExist.setUbigeo(model.getUbigeo());
        modelExist.setNombre(model.getNombre());
        modelExist.setEstado(model.getEstado());
        modelExist.setUsuarioModifica(user.getId());
        return veterinariaRepository.save(modelExist);
    }

    @Override
    public VeterinariaEntity findById(Long id) {
        return veterinariaRepository.findById(id).orElseThrow(()-> new ServiceException(ErrorCode.V002));
    }
}
