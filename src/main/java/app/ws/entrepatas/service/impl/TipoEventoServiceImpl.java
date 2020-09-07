package app.ws.entrepatas.service.impl;

import app.ws.entrepatas.enums.ErrorCode;
import app.ws.entrepatas.exception.NoExistEntityException;
import app.ws.entrepatas.exception.ServiceException;
import app.ws.entrepatas.model.RazaEntity;
import app.ws.entrepatas.model.TipoEventoEntity;
import app.ws.entrepatas.repository.TipoEventoRepository;
import app.ws.entrepatas.security.UserPrincipal;
import app.ws.entrepatas.service.TipoEventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TipoEventoServiceImpl implements TipoEventoService {

    @Autowired
    TipoEventoRepository tipoEventoRepository;

    @Override
    public TipoEventoEntity create(TipoEventoEntity model) {
        model.setFechaCreacion(LocalDateTime.now());
        return tipoEventoRepository.save(model);
    }

    @Override
    public List<TipoEventoEntity> findAll() {
        return tipoEventoRepository.findAll();
    }

    @Override
    public void delete(Long id) throws NoExistEntityException {
        Optional<TipoEventoEntity> raza = tipoEventoRepository.findById(id);
        if (raza.isPresent()) {
            tipoEventoRepository.delete(raza.get());
        } else {
            throw new NoExistEntityException("No existe");
        }
    }

    @Override
    public TipoEventoEntity update(TipoEventoEntity model, UserPrincipal user) {
        TipoEventoEntity modelExist = findById(model.getId());
        modelExist.setNombre(model.getNombre());
        modelExist.setEstado(model.getEstado());
        return tipoEventoRepository.save(modelExist);
    }

    @Override
    public TipoEventoEntity findById(Long id) {
        return tipoEventoRepository.findById(id).orElseThrow(()->new ServiceException(ErrorCode.V002));
    }
}
