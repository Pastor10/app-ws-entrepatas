package app.ws.entrepatas.controller;

import app.ws.entrepatas.dto.UsuarioDto;
import app.ws.entrepatas.exception.ServiceException;
import app.ws.entrepatas.model.UsuarioEntity;
import app.ws.entrepatas.security.CurrentUser;
import app.ws.entrepatas.security.UserPrincipal;
import app.ws.entrepatas.service.UsuarioService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;


@RestController
@RequestMapping("api/usuario")
@CrossOrigin("*")
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
    public ResponseEntity<Page<UsuarioDto>> findAll(@RequestHeader(value="Authorization") String authorization,
                                                    @RequestParam(name = "page") Integer page,
                                                    @RequestParam(name = "perPage") Integer perPage,
                                                    @RequestParam(name = "documento", required = false) String documento,
                                                    @RequestParam(name = "nombres", required = false) String  nombres) {
        Pageable pageable = PageRequest.of(page, perPage);
        return ResponseEntity.ok().body(usuarioService.findAll(nombres, documento, pageable).map(UsuarioDto::transformToDto));
    }

    @GetMapping("/integrantes")
    public List<UsuarioDto> findAllIntegrantes() {
        return UsuarioDto.transformToDtoIntegrantes(usuarioService.findAllIntegrantes());
    }

    @GetMapping("/findById/{id}")
    public UsuarioEntity findById(@RequestHeader(value="Authorization") String authorization,@PathVariable("id") Long id) {
        return usuarioService.findById(id);
    }


    @PutMapping("/validate-uuid/{uuid}")
    @ApiOperation(value = "Valida el codigo uuid que se le envio al correo")
    public ResponseEntity<Boolean> validateUuid(@PathVariable("uuid")  String uuid) throws ServiceException {
        return new ResponseEntity<>(usuarioService.validateUuid(uuid), HttpStatus.OK);

    }


}
