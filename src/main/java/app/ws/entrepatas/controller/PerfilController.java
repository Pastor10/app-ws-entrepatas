package app.ws.entrepatas.controller;

import app.ws.entrepatas.exception.NoExistEntityException;
import app.ws.entrepatas.model.PerfilEntity;
import app.ws.entrepatas.service.PerfilService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("api/perfil")
//@PreAuthorize("hasRole('PERFIL')")
@CrossOrigin("*")
public class PerfilController {

    @Autowired
    private PerfilService perfilService;

    @PostMapping("/create")
    @ApiOperation(value = "creacion de perfiles")
    public PerfilEntity create(@RequestHeader(value="Authorization") String authorization,@RequestBody @Valid PerfilEntity Perfil) {
        return perfilService.create(Perfil);
    }

    @PutMapping("/update")
    @ApiOperation(value = "actualizacion de perfiles")
    public PerfilEntity update(@RequestHeader(value="Authorization") String authorization,@RequestBody @Valid PerfilEntity Perfil) throws Exception {
        return perfilService.update(Perfil);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "eliminar perfil por id")
    public void delete(@RequestHeader(value="Authorization") String authorization,@PathVariable("id") Long id) {
        try {
            perfilService.delete(id);
        } catch (NoExistEntityException ex) {

        }
    }

    @GetMapping("/findAll")
    @ApiOperation(value = "listar perfiles")
    public List<PerfilEntity> findAll(@RequestHeader(value="Authorization") String authorization) {
        return perfilService.findAll();
    }

    @GetMapping("/findById/{id}")
    @ApiOperation(value = "buscar perfil por id")
    public PerfilEntity findById(@RequestHeader(value="Authorization") String authorization,@PathVariable("id") Long id) {
        return perfilService.findById(id).get();
    }

}
