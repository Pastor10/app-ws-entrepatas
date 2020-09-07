package app.ws.entrepatas.service;

import app.ws.entrepatas.dto.response.PublicacionResponseDto;

import java.util.List;

public interface ReporteService {
    List<PublicacionResponseDto> findCondicion(Long idCondicion);
}
