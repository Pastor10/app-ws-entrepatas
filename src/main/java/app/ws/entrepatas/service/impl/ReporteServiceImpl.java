package app.ws.entrepatas.service.impl;

import app.ws.entrepatas.dto.response.PublicacionResponseDto;
import app.ws.entrepatas.jdbc.PublicacionNativeRepository;
import app.ws.entrepatas.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReporteServiceImpl implements ReporteService {

    @Autowired
    PublicacionNativeRepository publicacionNativeRepository;

    @Override
    public List<PublicacionResponseDto> findCondicion(Long idCondicion) {
        return publicacionNativeRepository.findAllByCondicion(idCondicion);
    }
}
