package app.ws.entrepatas.controller;

import app.ws.entrepatas.model.VeterinarioEntity;
import app.ws.entrepatas.security.CurrentUser;
import app.ws.entrepatas.security.UserPrincipal;
import app.ws.entrepatas.service.VeterinarioService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequestMapping("api/veterinario")
@CrossOrigin("*")
public class VeterinarioController {

    @Autowired
    VeterinarioService veterinarioService;

    @PostMapping("/create")
    @ApiOperation(value = "creacion de  veterinario ")
    public VeterinarioEntity create(@RequestHeader(value="Authorization") String authorization, @ApiIgnore @CurrentUser UserPrincipal user, @RequestBody VeterinarioEntity veterinario) {
        return veterinarioService.create(veterinario, user);
    }

    @GetMapping("/findAll")
    @ApiOperation(value = "listado de  veterinarios ")
    public List<VeterinarioEntity> findAll(@RequestHeader(value="Authorization") String authorization) {
        return veterinarioService.findAll();
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "eliminar  veterinario por id ")
    public void delete(@RequestHeader(value="Authorization") String authorization, @ApiIgnore @CurrentUser UserPrincipal user,@PathVariable("id") Long id) {
       veterinarioService.delete(id, user);
    }

    @PutMapping("/update")
    @ApiOperation(value = "actualizar  veterinario ")
    public VeterinarioEntity update(@RequestHeader(value="Authorization") String authorization, @ApiIgnore @CurrentUser UserPrincipal user,@RequestBody VeterinarioEntity veterinario) throws Exception {
        return veterinarioService.update(veterinario, user);
    }
}
