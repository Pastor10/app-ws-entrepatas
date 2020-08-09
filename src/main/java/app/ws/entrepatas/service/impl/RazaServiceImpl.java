package app.ws.entrepatas.service.impl;

import app.ws.entrepatas.exception.NoExistEntityException;
import app.ws.entrepatas.model.RazaEntity;
import app.ws.entrepatas.repository.RazaRepository;
import app.ws.entrepatas.service.RazaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RazaServiceImpl implements RazaService {

    @Autowired
    RazaRepository razaRepository;

    @Override
    public RazaEntity create(RazaEntity o) {
        o.setId(null);
        o.setFechaCreacion(LocalDateTime.now());
        RazaEntity u = razaRepository.save(o);
        return u;
    }

    @Override
    public RazaEntity update(RazaEntity o) throws NoExistEntityException {
        Optional<RazaEntity> razaSearch = findById(o.getId());
        if(razaSearch.isPresent()) {
            RazaEntity u = razaRepository.save(o);
            return u;
        }else{
            throw new NoExistEntityException("No existe");
        }
    }

    @Override
    public void delete(Long id) throws NoExistEntityException {
        Optional<RazaEntity> raza = razaRepository.findById(id);
        if (raza.isPresent()) {
            razaRepository.delete(raza.get());
        } else {
            throw new NoExistEntityException("No existe");
        }
    }

    @Override
    public Optional<RazaEntity> findById(Long id) {
        Optional<RazaEntity> result = razaRepository.findById(id);
        if(result.isPresent()) {
            return result;
        }else {
            return null;
        }
    }

    @Override
    public List<RazaEntity> findAll() {
        return razaRepository.findAll();
    }

    @Override
    public List<RazaEntity> findAllById(Long id) {
        List<RazaEntity> listRaza = razaRepository.findAllByTipoAnimal_Id(id);
        return listRaza;
    }
}
