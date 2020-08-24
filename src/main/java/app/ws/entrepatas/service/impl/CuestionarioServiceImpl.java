package app.ws.entrepatas.service.impl;

import app.ws.entrepatas.enums.ErrorCode;
import app.ws.entrepatas.exception.ServiceException;
import app.ws.entrepatas.model.CuestionarioEntity;
import app.ws.entrepatas.model.DetalleCuestionarioEntity;
import app.ws.entrepatas.model.OpcionEntity;
import app.ws.entrepatas.model.PostulanteEntity;
import app.ws.entrepatas.model.TipoCuestionarioEntity;
import app.ws.entrepatas.repository.CuestionarioRepository;
import app.ws.entrepatas.repository.OpcionRepository;
import app.ws.entrepatas.repository.PostulanteRepository;
import app.ws.entrepatas.security.UserPrincipal;
import app.ws.entrepatas.service.CuestionarioService;
import app.ws.entrepatas.service.PostulanteService;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
    public CuestionarioEntity create(CuestionarioEntity cuestionario, UserPrincipal user) {
        Integer promedio=0;
        Integer suma=0;
        for (DetalleCuestionarioEntity item: cuestionario.getListaDetalle()) {
            item.setCuestionario(cuestionario);
            OpcionEntity  opcion = opcionRepository.findById(item.getOpcion().getId()).orElseThrow(()-> new ServiceException(ErrorCode.V002));
            suma = suma + opcion.getPuntaje();
        }
        promedio = suma/4;


        cuestionario.setPromedio(promedio);
        cuestionario.setFechaCreacion(LocalDateTime.now());
        cuestionario.setTipoCuestionario(TipoCuestionarioEntity.builder().id(1l).build());
        cuestionario.setEliminado(Boolean.FALSE);
        cuestionario.setUsuarioCrea(user.getId());
        cuestionarioRepository.save(cuestionario);

        PostulanteEntity postulante = postulanteRepository.findById(cuestionario.getIdPostulante()).orElseThrow(()->new ServiceException(ErrorCode.V002));
        postulante.setPuntuacion(promedio);
        postulante.setFechaModificacion(LocalDateTime.now());
        postulante.setCuestionario(cuestionario);
        postulanteRepository.save(postulante);
        return cuestionario;
    }

    @Override
    public CuestionarioEntity update(CuestionarioEntity cuestionario, UserPrincipal user) {
        CuestionarioEntity modelExist = findById(cuestionario.getId());
        Integer promedio=0;
        Integer suma=0;
        for (DetalleCuestionarioEntity item: cuestionario.getListaDetalle()) {
            item.setCuestionario(cuestionario);
            OpcionEntity  opcion = opcionRepository.findById(item.getOpcion().getId()).orElseThrow(()-> new ServiceException(ErrorCode.V002));
            item.setOpcion(opcion);
            suma = suma + opcion.getPuntaje();
        }

        modelExist.setListaDetalle(cuestionario.getListaDetalle());

        promedio = suma/4;
        cuestionario.setPromedio(promedio);
        modelExist.setFechaModificacion(LocalDateTime.now());
        modelExist.setUsuarioModifica(user.getId());

        cuestionarioRepository.save(modelExist);
        postulanteRepository.update(cuestionario.getIdPostulante(), user.getId(),LocalDateTime.now(),promedio );
        return modelExist;
    }

    @Override
    public CuestionarioEntity findById(Long id) {
        return cuestionarioRepository.findById(id).orElseThrow(()->new ServiceException(ErrorCode.V002));
    }
}
