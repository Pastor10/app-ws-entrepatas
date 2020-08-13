package app.ws.entrepatas.controller;

import app.ws.entrepatas.dto.UsuarioDto;
import app.ws.entrepatas.model.UsuarioEntity;
import app.ws.entrepatas.security.CurrentUser;
import app.ws.entrepatas.security.UserPrincipal;
import app.ws.entrepatas.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("api/usuario")
//@PreAuthorize("hasRole('USER')")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;


    @PostMapping("/create")
    public UsuarioEntity create(@RequestHeader(value="Authorization") String authorization,@RequestBody UsuarioEntity usuario, @ApiIgnore @CurrentUser UserPrincipal user) {
        return usuarioService.create(usuario, user);
    }

    @PostMapping("/create-visitante")
    public UsuarioEntity createVisitante(@RequestBody UsuarioEntity usuario) {
        return usuarioService.createVisitante(usuario);
    }

    @PutMapping("/update")
    public UsuarioEntity update(@RequestHeader(value="Authorization") String authorization,@RequestBody UsuarioEntity usuario,@ApiIgnore @CurrentUser UserPrincipal user)  {
        return usuarioService.update(usuario, user);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@RequestHeader(value="Authorization") String authorization,@PathVariable("id") Long id,@ApiIgnore @CurrentUser UserPrincipal user) {
            usuarioService.delete(id, user);

    }

    @GetMapping("/findAll")
    public List<UsuarioDto> findAll(@RequestHeader(value="Authorization") String authorization) {
        return UsuarioDto.transformToDto(usuarioService.findAll());
    }

    @GetMapping("/integrantes")
    public List<UsuarioDto> findAllIntegrantes() {
        return UsuarioDto.transformToDtoIntegrantes(usuarioService.findAll());
    }

    @GetMapping("/findById/{id}")
    public UsuarioEntity findById(@RequestHeader(value="Authorization") String authorization,@PathVariable("id") Long id) {
        return usuarioService.findById(id);
    }



}
