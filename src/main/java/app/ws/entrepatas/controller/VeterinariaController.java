package app.ws.entrepatas.controller;


import app.ws.entrepatas.model.VeterinariaEntity;
import app.ws.entrepatas.security.CurrentUser;
import app.ws.entrepatas.security.UserPrincipal;
import app.ws.entrepatas.service.VeterinariaService;
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
@RequestMapping("api/veterinaria")
public class VeterinariaController {

    @Autowired
    VeterinariaService veterinariaService;

    @PostMapping("/create")
    public VeterinariaEntity create(@RequestHeader(value="Authorization") String authorization, @ApiIgnore @CurrentUser UserPrincipal user,@RequestBody VeterinariaEntity veterinaria) {
        return veterinariaService.create(veterinaria, user);
    }

    @GetMapping("/findAll")
    public List<VeterinariaEntity> findAll(@RequestHeader(value="Authorization") String authorization) {
        return veterinariaService.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@RequestHeader(value="Authorization") String authorization, @ApiIgnore @CurrentUser UserPrincipal user,@PathVariable("id") Long id) {
        veterinariaService.delete(id, user);
    }

    @PutMapping("/update")
    public VeterinariaEntity update(@RequestHeader(value="Authorization") String authorization, @ApiIgnore @CurrentUser UserPrincipal user,@RequestBody VeterinariaEntity model) {
        return  veterinariaService.update(model, user);

    }
}
