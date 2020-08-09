package app.ws.entrepatas.controller;

import app.ws.entrepatas.exception.NoExistEntityException;
import app.ws.entrepatas.model.PerfilEntity;
import app.ws.entrepatas.model.TipoLocalEntity;
import app.ws.entrepatas.security.CurrentUser;
import app.ws.entrepatas.security.UserPrincipal;
import app.ws.entrepatas.service.TipoLocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/tipolocal")
public class TipoLocalController {

    @Autowired
    TipoLocalService tipoLocalService;

    @GetMapping("findAll")
    public List<TipoLocalEntity> findAll(@RequestHeader(value="Authorization") String authorization){
        return tipoLocalService.findAll();
    }

    @PostMapping("/create")
    public TipoLocalEntity create(@RequestHeader(value="Authorization") String authorization,@RequestBody @Valid TipoLocalEntity tipoLocal) {
        return tipoLocalService.create(tipoLocal);
    }

    @PutMapping("/update")
    public TipoLocalEntity update(@RequestHeader(value="Authorization") String authorization,@RequestBody @Valid TipoLocalEntity tipoLocal) throws Exception {
        return tipoLocalService.update(tipoLocal);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@RequestHeader(value="Authorization") String authorization,@PathVariable("id") Long id) {
        try {
            tipoLocalService.delete(id);
        } catch (NoExistEntityException ex) {

        }
    }
}
