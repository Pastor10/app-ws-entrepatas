package app.ws.entrepatas.controller;


import app.ws.entrepatas.dto.CondicionDto;
import app.ws.entrepatas.model.CondicionEntity;
import app.ws.entrepatas.service.CondicionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/condicion")
@CrossOrigin("*")
public class CondicionController {

    @Autowired
    CondicionService condicionService;

    @GetMapping("findAll")
    @ApiOperation(value = "listamos las conciones de los animales")
    public List<CondicionEntity> findAll(@RequestHeader(value="Authorization") String authorization){
        return condicionService.findAll();
    }

    @GetMapping("findAllVisitante")
    @ApiOperation(value = "listamos las conciones de los animales para el perfil visitante")
    public List<CondicionDto> findAllVisitante(@RequestHeader(value="Authorization") String authorization){
        return CondicionDto.transformToDto(condicionService.findAll());
    }
}
