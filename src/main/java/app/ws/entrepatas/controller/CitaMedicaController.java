package app.ws.entrepatas.controller;

import app.ws.entrepatas.dto.CitaMedicaDto;
import app.ws.entrepatas.model.CitaMedicaEntity;
import app.ws.entrepatas.model.EventoEntity;
import app.ws.entrepatas.security.CurrentUser;
import app.ws.entrepatas.security.UserPrincipal;
import app.ws.entrepatas.service.CitaMedicaService;
import app.ws.entrepatas.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("api/cita-medica")
public class CitaMedicaController {

    @Autowired
    CitaMedicaService citaMedicaService;

    @PostMapping("/create")
    public CitaMedicaDto create(@RequestBody CitaMedicaEntity cita) {
        return CitaMedicaDto.transformToDto(citaMedicaService.create(cita)) ;
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@RequestHeader(value="Authorization") String authorization, @PathVariable("id") Long id, @ApiIgnore @CurrentUser UserPrincipal user) {
        citaMedicaService.delete(id, user);

    }
}
