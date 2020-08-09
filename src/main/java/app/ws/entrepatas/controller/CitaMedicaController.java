package app.ws.entrepatas.controller;

import app.ws.entrepatas.dto.CitaMedicaDto;
import app.ws.entrepatas.model.CitaMedicaEntity;
import app.ws.entrepatas.model.EventoEntity;
import app.ws.entrepatas.service.CitaMedicaService;
import app.ws.entrepatas.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/cita-medica")
public class CitaMedicaController {

    @Autowired
    CitaMedicaService citaMedicaService;

    @PostMapping("/create")
    public CitaMedicaDto create(@RequestBody CitaMedicaEntity cita) {
        return CitaMedicaDto.transformToDto(citaMedicaService.create(cita)) ;
    }
}
