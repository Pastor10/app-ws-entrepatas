package app.ws.entrepatas.service.impl;

import app.ws.entrepatas.model.PostulanteColaboradorEntity;
import app.ws.entrepatas.repository.PostulanteColaboradorRepository;
import app.ws.entrepatas.service.PostulanteColaboradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostulanteColaboradorServiceImpl implements PostulanteColaboradorService {

    @Autowired
    PostulanteColaboradorRepository postulanteColaboradorRepository;

    @Override
    public List<PostulanteColaboradorEntity> findAll() {
        return postulanteColaboradorRepository.findAll();
    }
}
