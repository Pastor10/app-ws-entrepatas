package app.ws.entrepatas.controller;

import app.ws.entrepatas.dto.PostulanteColaboradorDto;
import app.ws.entrepatas.service.PostulanteColaboradorService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/postulante-colaborador")
@CrossOrigin("*")
public class PostulanteColaboradorController {

    @Autowired
    PostulanteColaboradorService postulanteColaboradorService;

    @PreAuthorize("hasAuthority('ROLE_POSTULANTE_COLABORADOR')")
    @GetMapping("/findAll")
    @ApiOperation(value = "listado de todas solicitudes de voluntarios")
    public List<PostulanteColaboradorDto> findAll(@RequestHeader(value="Authorization") String authorization) {
        return PostulanteColaboradorDto.transformToDto(postulanteColaboradorService.findAll());
    }
}
