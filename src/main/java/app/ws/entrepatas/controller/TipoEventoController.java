package app.ws.entrepatas.controller;

import app.ws.entrepatas.exception.NoExistEntityException;
import app.ws.entrepatas.model.TipoEventoEntity;
import app.ws.entrepatas.security.CurrentUser;
import app.ws.entrepatas.security.UserPrincipal;
import app.ws.entrepatas.service.TipoEventoService;
import com.amazonaws.services.s3.AmazonS3;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
@RequestMapping("api/tipoEvento")
@CrossOrigin("*")
public class TipoEventoController {

    @Autowired
    TipoEventoService tipoEventoService;


    @PreAuthorize("hasAuthority('ROLE_EVENTO_TIPO')")
    @PostMapping("/create")
    @ApiOperation(value = "creacion tipo evento")
    public TipoEventoEntity create(@RequestHeader(value="Authorization") String authorization, @RequestBody TipoEventoEntity tipoEvento) {
        return tipoEventoService.create(tipoEvento);

    }

    @PreAuthorize("hasAuthority('ROLE_EVENTO_TIPO')")
    @GetMapping("findAll")
    @ApiOperation(value = "listado tipo evento")
    public List<TipoEventoEntity> findAll(@RequestHeader(value="Authorization") String authorization){
        return tipoEventoService.findAll();
    }

    @PreAuthorize("hasAuthority('ROLE_EVENTO_TIPO')")
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "eliminar tipo evento por id")
    public void delete(@RequestHeader(value="Authorization") String authorization,@PathVariable("id") Long id) {
        try {
            tipoEventoService.delete(id);
        } catch (NoExistEntityException ex) {
            ex.printStackTrace();
        }
    }

    @PreAuthorize("hasAuthority('ROLE_EVENTO_TIPO')")
    @PutMapping("/update")
    @ApiOperation(value = "actualizar tipo evento")
    public TipoEventoEntity update(@RequestHeader(value="Authorization") String authorization, @RequestBody TipoEventoEntity tipoEvento, @ApiIgnore @CurrentUser UserPrincipal user) {
        return tipoEventoService.update(tipoEvento, user);
    }



}
