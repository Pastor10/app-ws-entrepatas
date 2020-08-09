package app.ws.entrepatas.service.impl;

import app.ws.entrepatas.model.AnimalEntity;
import app.ws.entrepatas.repository.AnimalRepository;
import app.ws.entrepatas.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalServiceImpl implements AnimalService {

    @Autowired
    AnimalRepository animalRepository;

    @Override
    public List<AnimalEntity> findAll() {
        return animalRepository.findAll();
    }

    @Override
    public Optional<AnimalEntity> findById(Long id) {
        return animalRepository.findById(id);
    }
}
