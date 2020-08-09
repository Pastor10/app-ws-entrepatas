package app.ws.entrepatas.service.impl;

import app.ws.entrepatas.enums.ErrorCode;
import app.ws.entrepatas.exception.NoExistEntityException;
import app.ws.entrepatas.exception.ServiceException;
import app.ws.entrepatas.model.VeterinarioEntity;
import app.ws.entrepatas.repository.VeterinarioRepository;
import app.ws.entrepatas.security.UserPrincipal;
import app.ws.entrepatas.service.VeterinarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class VeterinarioServiceImpl  implements VeterinarioService {

    @Autowired
    VeterinarioRepository veterinarioRepository;

    @Override
    public List<VeterinarioEntity> findAll() {
        return veterinarioRepository.findAll();
    }

    @Override
    public VeterinarioEntity create(VeterinarioEntity model, UserPrincipal user) {
        model.setFechaCreacion(LocalDateTime.now());
        model.setUsuarioCrea(user.getId());
        return veterinarioRepository.save(model);
    }

    @Override
    public void delete(Long id, UserPrincipal user)  {
       veterinarioRepository.delete(id, user.getId(), LocalDateTime.now());
    }

    @Override
    public VeterinarioEntity update(VeterinarioEntity model, UserPrincipal user) {
        VeterinarioEntity modelExist = findById(model.getId());
        modelExist.setEstado(model.getEstado());
        modelExist.setNombre(model.getNombre());
        modelExist.setVeterinaria(model.getVeterinaria());
        modelExist.setFechaModificacion(LocalDateTime.now());
        modelExist.setUsuarioModifica(user.getId());
        return veterinarioRepository.save(modelExist);
    }

    @Override
    public VeterinarioEntity findById(Long id) {
        return veterinarioRepository.findById(id).orElseThrow(()->new ServiceException(ErrorCode.V002));

    }
}
