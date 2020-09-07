package app.ws.entrepatas.controller;

import app.ws.entrepatas.dto.CitaMedicaDto;
import app.ws.entrepatas.model.CitaMedicaEntity;
import app.ws.entrepatas.security.CurrentUser;
import app.ws.entrepatas.security.UserPrincipal;
import app.ws.entrepatas.service.CitaMedicaService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin("*")
public class CitaMedicaController {

    @Autowired
    CitaMedicaService citaMedicaService;

    @PreAuthorize("hasAuthority('ROLE_PUBLICACION_HISTORIAL_CLINICO')")
    @PostMapping("/create")
    @ApiOperation(value = "registramos una cita medica del animal")
    public CitaMedicaDto create(@RequestHeader(value="Authorization") String authorization,@RequestBody CitaMedicaEntity cita) {
        return CitaMedicaDto.transformToDto(citaMedicaService.create(cita)) ;
    }

    @PreAuthorize("hasAuthority('ROLE_PUBLICACION_HISTORIAL_CLINICO')")
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "eliminamos una cita medica del animal por id")
    public void delete(@RequestHeader(value="Authorization") String authorization, @PathVariable("id") Long id, @ApiIgnore @CurrentUser UserPrincipal user) {
        citaMedicaService.delete(id, user);

    }
}
