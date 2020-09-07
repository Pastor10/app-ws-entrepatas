package app.ws.entrepatas.controller;


import app.ws.entrepatas.dto.LocalDto;
import app.ws.entrepatas.model.LocalEntity;
import app.ws.entrepatas.security.CurrentUser;
import app.ws.entrepatas.security.UserPrincipal;
import app.ws.entrepatas.service.LocalService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequestMapping("api/local")
@CrossOrigin("*")
public class LocalController {

    @Autowired
    LocalService localService;


    @PreAuthorize("hasAuthority('ROLE_LOCAL_CREA')")
    @GetMapping("findAll")
    @ApiOperation(value = "listamos todos los locales")
    public List<LocalEntity> findAll(@RequestHeader(value="Authorization") String authorization){
        return  localService.findAll();
    }

    @PreAuthorize("hasAuthority('ROLE_LOCAL_CREA')")
    @GetMapping("findAllDisponibles")
    @ApiOperation(value = "listamos todos los locales disponibles para crear publicacion")
    public List<LocalDto> findAllDisponibles(@RequestHeader(value="Authorization") String authorization){
        return  LocalDto.transformToDto(localService.findAll());
    }

    @PreAuthorize("hasAuthority('ROLE_LOCAL_CREA')")
    @PostMapping("/create")
    @ApiOperation(value = "creacion de locales")
    public LocalEntity create(@RequestHeader(value="Authorization") String authorization, @RequestBody LocalEntity local, @ApiIgnore @CurrentUser UserPrincipal user) {
        return localService.create(local, user);
    }

    @PreAuthorize("hasAuthority('ROLE_LOCAL_CREA')")
    @PutMapping("/update")
    @ApiOperation(value = "actualizacion de datos de locales")
    public LocalEntity update(@RequestHeader(value="Authorization") String authorization,@RequestBody LocalEntity local, @ApiIgnore @CurrentUser UserPrincipal user) {
        return localService.update(local, user);
    }
}
