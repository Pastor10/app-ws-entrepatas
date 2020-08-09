package app.ws.entrepatas.service.impl;

import app.ws.entrepatas.exception.NoExistEntityException;
import app.ws.entrepatas.model.TamanoAnimalEntity;
import app.ws.entrepatas.model.TipoAnimalEntity;
import app.ws.entrepatas.repository.TipoAnimalRepository;
import app.ws.entrepatas.service.TipoAnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class TipoAnimalServiceImpl implements TipoAnimalService {

    @Autowired
    TipoAnimalRepository tipoAnimalRepository;

    @Override
    public TipoAnimalEntity create(TipoAnimalEntity o) {
        o.setId(null);
        o.setFechaCreacion(LocalDateTime.now());
        TipoAnimalEntity u = tipoAnimalRepository.save(o);
        return u;
    }

    @Override
    public TipoAnimalEntity update(TipoAnimalEntity o) throws NoExistEntityException {
        Optional<TipoAnimalEntity> tipoSearch = findById(o.getId());
        if(tipoSearch.isPresent()) {
            TipoAnimalEntity u = tipoAnimalRepository.save(o);
            return u;
        }else{
            throw new NoExistEntityException("No existe");
        }
    }

    @Override
    public void delete(Long id) throws NoExistEntityException {
        Optional<TipoAnimalEntity> tipo = tipoAnimalRepository.findById(id);
        if (tipo.isPresent()) {
            tipoAnimalRepository.delete(tipo.get());
        } else {
            throw new NoExistEntityException("No existe");
        }
    }

    @Override
    public Optional<TipoAnimalEntity> findById(Long id) {
        Optional<TipoAnimalEntity> result = tipoAnimalRepository.findById(id);
        if(result.isPresent()) {
            return result;
        }else {
            return null;
        }
    }

    @Override
    public List<TipoAnimalEntity> findAll() {
        return tipoAnimalRepository.findAll();
    }
}
