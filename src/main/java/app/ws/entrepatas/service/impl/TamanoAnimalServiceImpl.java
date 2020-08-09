package app.ws.entrepatas.service.impl;

import app.ws.entrepatas.exception.NoExistEntityException;
import app.ws.entrepatas.model.TamanoAnimalEntity;
import app.ws.entrepatas.repository.TamanoAnimalRepository;
import app.ws.entrepatas.service.TamanoAnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TamanoAnimalServiceImpl  implements TamanoAnimalService {

    @Autowired
    TamanoAnimalRepository tamanoAnimalRepository;

    @Override
    public TamanoAnimalEntity create(TamanoAnimalEntity o) {
        o.setId(null);
        o.setFechaCreacion(LocalDateTime.now());
        TamanoAnimalEntity u = tamanoAnimalRepository.save(o);
        return u;
    }

    @Override
    public TamanoAnimalEntity update(TamanoAnimalEntity o) throws NoExistEntityException {
        Optional<TamanoAnimalEntity> tamanoSearch = findById(o.getId());
        if(tamanoSearch.isPresent()) {
            TamanoAnimalEntity u = tamanoAnimalRepository.save(o);
            return u;
        }else{
            throw new NoExistEntityException("No existe");
        }
    }

    @Override
    public void delete(Long id) throws NoExistEntityException {
        Optional<TamanoAnimalEntity> tamano = tamanoAnimalRepository.findById(id);
        if (tamano.isPresent()) {
            tamanoAnimalRepository.delete(tamano.get());
        } else {
            throw new NoExistEntityException("No existe");
        }
    }

    @Override
    public Optional<TamanoAnimalEntity> findById(Long id) {
        Optional<TamanoAnimalEntity> result = tamanoAnimalRepository.findById(id);
        if(result.isPresent()) {
            return result;
        }else {
            return null;
        }
    }

    @Override
    public List<TamanoAnimalEntity> findAll() {
        return tamanoAnimalRepository.findAll();
    }
}
