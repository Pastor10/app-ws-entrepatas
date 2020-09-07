package app.ws.entrepatas.controller;

import app.ws.entrepatas.exception.NoExistEntityException;
import app.ws.entrepatas.model.TipoLocalEntity;
import app.ws.entrepatas.service.TipoLocalService;
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
@RequestMapping("api/tipolocal")
@CrossOrigin("*")
public class TipoLocalController {

    @Autowired
    TipoLocalService tipoLocalService;

    @GetMapping("findAll")
    @ApiOperation(value = "listar tipo local")
    public List<TipoLocalEntity> findAll(@RequestHeader(value="Authorization") String authorization){
        return tipoLocalService.findAll();
    }

    @PostMapping("/create")
    @ApiOperation(value = "crear tipo local")
    public TipoLocalEntity create(@RequestHeader(value="Authorization") String authorization,@RequestBody @Valid TipoLocalEntity tipoLocal) {
        return tipoLocalService.create(tipoLocal);
    }

    @PutMapping("/update")
    @ApiOperation(value = "actualizar tipo local")
    public TipoLocalEntity update(@RequestHeader(value="Authorization") String authorization,@RequestBody @Valid TipoLocalEntity tipoLocal) throws Exception {
        return tipoLocalService.update(tipoLocal);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "eliminar tipo local por id")
    public void delete(@RequestHeader(value="Authorization") String authorization,@PathVariable("id") Long id) {
        try {
            tipoLocalService.delete(id);
        } catch (NoExistEntityException ex) {
            ex.printStackTrace();
        }
    }
}
