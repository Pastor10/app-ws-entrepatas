package app.ws.entrepatas.service.impl;

import app.ws.entrepatas.enums.ErrorCode;
import app.ws.entrepatas.exception.ServiceException;
import app.ws.entrepatas.model.CuestionarioEntity;
import app.ws.entrepatas.model.OpcionEntity;
import app.ws.entrepatas.model.PostulanteEntity;
import app.ws.entrepatas.repository.CuestionarioRepository;
import app.ws.entrepatas.repository.OpcionRepository;
import app.ws.entrepatas.repository.PostulanteRepository;
import app.ws.entrepatas.service.CuestionarioService;
import app.ws.entrepatas.service.PostulanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CuestionarioServiceImpl implements CuestionarioService {

    @Autowired
    CuestionarioRepository cuestionarioRepository;

    @Autowired
    OpcionRepository opcionRepository;

    @Autowired
    PostulanteService postulanteService;

   @Autowired
    PostulanteRepository postulanteRepository;

    @Override
    public List<CuestionarioEntity> create(List<CuestionarioEntity> lista) {
        Integer promedio=0;
        Integer suma=0;
        Long idPostulante=0L;
        for (CuestionarioEntity item:lista) {
            item.setFechaCreacion(LocalDateTime.now());
            item.setEliminado(Boolean.FALSE);
            idPostulante = item.getPostulante().getId();
            OpcionEntity  opcion = opcionRepository.findById(item.getOpcion().getId()).orElseThrow(()-> new ServiceException(ErrorCode.V002));
            suma = suma + opcion.getPuntaje();
        }

        promedio = suma/4;
        PostulanteEntity postulante = postulanteService.findById(idPostulante);
        postulante.setFechaModificacion(LocalDateTime.now());
        postulante.setPuntuacion(promedio);
        postulanteRepository.save(postulante);

        return cuestionarioRepository.saveAll(lista);
    }
}
