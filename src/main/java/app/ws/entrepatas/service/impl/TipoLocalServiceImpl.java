package app.ws.entrepatas.service.impl;

import app.ws.entrepatas.enums.ErrorCode;
import app.ws.entrepatas.exception.NoExistEntityException;
import app.ws.entrepatas.exception.ServiceException;
import app.ws.entrepatas.model.PerfilEntity;
import app.ws.entrepatas.model.TipoLocalEntity;
import app.ws.entrepatas.model.UsuarioEntity;
import app.ws.entrepatas.repository.TipoLocalRepository;
import app.ws.entrepatas.service.TipoLocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TipoLocalServiceImpl implements TipoLocalService {

    @Autowired
    TipoLocalRepository tipoLocalRepository;

    @Override
    public List<TipoLocalEntity> findAll() {
        return tipoLocalRepository.findAll();
    }

    @Override
    public TipoLocalEntity create(TipoLocalEntity model) {
        model.setFechaCreacion(LocalDateTime.now());
        return tipoLocalRepository.save(model);

    }

    @Override
    public TipoLocalEntity update(TipoLocalEntity model) {
        TipoLocalEntity modelExits = findById(model.getId());
        modelExits.setNombre(model.getNombre());
        return tipoLocalRepository.save(modelExits);
    }

    @Override
    public void delete(Long id) throws NoExistEntityException {
        Optional<TipoLocalEntity> o = tipoLocalRepository.findById(id);
        if (o.isPresent()) {
            tipoLocalRepository.delete(o.get());
        } else {
            throw new NoExistEntityException("No existe");
        }
    }

    @Override
    public TipoLocalEntity findById(Long id) {
        return tipoLocalRepository.findById(id).orElseThrow(()->new ServiceException(ErrorCode.V002));
    }
}
