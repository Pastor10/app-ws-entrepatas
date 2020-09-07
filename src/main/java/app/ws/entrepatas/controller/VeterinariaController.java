package app.ws.entrepatas.controller;


import app.ws.entrepatas.model.VeterinariaEntity;
import app.ws.entrepatas.security.CurrentUser;
import app.ws.entrepatas.security.UserPrincipal;
import app.ws.entrepatas.service.VeterinariaService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequestMapping("api/veterinaria")
@CrossOrigin("*")
public class VeterinariaController {

    @Autowired
    VeterinariaService veterinariaService;

    @PreAuthorize("hasAuthority('ROLE_VETERINARIA')")
    @PostMapping("/create")
    @ApiOperation(value = "creacion de veterinaria")
    public VeterinariaEntity create(@RequestHeader(value="Authorization") String authorization, @ApiIgnore @CurrentUser UserPrincipal user,@RequestBody VeterinariaEntity veterinaria) {
        return veterinariaService.create(veterinaria, user);
    }

    @PreAuthorize("hasAuthority('ROLE_VETERINARIA')")
    @GetMapping("/findAll")
    @ApiOperation(value = "listado de veterinarias")
    public List<VeterinariaEntity> findAll(@RequestHeader(value="Authorization") String authorization) {
        return veterinariaService.findAll();
    }

    @PreAuthorize("hasAuthority('ROLE_VETERINARIA')")
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "eliminar  veterinaria por id")
    public void delete(@RequestHeader(value="Authorization") String authorization, @ApiIgnore @CurrentUser UserPrincipal user,@PathVariable("id") Long id) {
        veterinariaService.delete(id, user);
    }

    @PreAuthorize("hasAuthority('ROLE_VETERINARIA')")
    @PutMapping("/update")
    @ApiOperation(value = "actualizar  veterinaria ")
    public VeterinariaEntity update(@RequestHeader(value="Authorization") String authorization, @ApiIgnore @CurrentUser UserPrincipal user,@RequestBody VeterinariaEntity model) {
        return  veterinariaService.update(model, user);

    }
}
