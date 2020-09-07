package app.ws.entrepatas.controller;

import app.ws.entrepatas.dto.CuestionarioDto;
import app.ws.entrepatas.model.CuestionarioEntity;
import app.ws.entrepatas.security.CurrentUser;
import app.ws.entrepatas.security.UserPrincipal;
import app.ws.entrepatas.service.CuestionarioService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;


@RestController
@RequestMapping("api/cuestionario")
@CrossOrigin("*")
public class CuestionarioController {

    @Autowired
    CuestionarioService cuestionarioService;

    @PostMapping("/create")
    @ApiOperation(value = "creamos el cuestionario para el postulante")
    public CuestionarioDto create(@RequestHeader(value="Authorization") String authorization,
                                  @RequestBody CuestionarioEntity cuestionario,  @ApiIgnore @CurrentUser UserPrincipal user) {
        return CuestionarioDto.transformToDto(cuestionarioService.create(cuestionario, user));
    }

    @PutMapping("/update")
    @ApiOperation(value = "actualiza el cuestionario para el postulante")
    public CuestionarioDto update(@RequestHeader(value="Authorization") String authorization, @RequestBody CuestionarioEntity cuestionario, @ApiIgnore @CurrentUser UserPrincipal user) {
        return CuestionarioDto.transformToDto(cuestionarioService.update(cuestionario, user));
    }
}
