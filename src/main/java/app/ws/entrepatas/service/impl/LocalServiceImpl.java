package app.ws.entrepatas.service.impl;

import app.ws.entrepatas.enums.ErrorCode;
import app.ws.entrepatas.exception.ServiceException;
import app.ws.entrepatas.model.LocalEntity;
import app.ws.entrepatas.repository.LocalRepository;
import app.ws.entrepatas.security.UserPrincipal;
import app.ws.entrepatas.service.LocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LocalServiceImpl implements LocalService {

    @Autowired
    LocalRepository localRepository;

    @Override
    public List<LocalEntity> findAll() {
        return localRepository.findAll();
    }

    @Override
    public LocalEntity create(LocalEntity model, UserPrincipal user) {
        model.setFechaCreacion(LocalDateTime.now());
        model.setUsuarioCrea(user.getId());
        model.setDisponible(Boolean.TRUE);
        return localRepository.save(model);

    }

    @Override
    public LocalEntity update(LocalEntity model, UserPrincipal user) {
        LocalEntity modelExist = findById(model.getId());
        modelExist.setUsuario(model.getUsuario());
        modelExist.setTipoLocal(model.getTipoLocal());
        modelExist.setCapacidad(model.getCapacidad());
        modelExist.setDireccion(model.getDireccion());
        modelExist.setUbigeo(model.getUbigeo());
        modelExist.setNombre(model.getNombre());
        modelExist.setUsuarioModifica(user.getId());
        modelExist.setFechaModificacion(LocalDateTime.now());
        return localRepository.save(modelExist);
    }

    @Override
    public LocalEntity findById(Long id) {
        return localRepository.findById(id).orElseThrow(()->new ServiceException(ErrorCode.V002));
    }
}
