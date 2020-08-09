package app.ws.entrepatas.controller;


import app.ws.entrepatas.model.LocalEntity;
import app.ws.entrepatas.model.UsuarioEntity;
import app.ws.entrepatas.security.CurrentUser;
import app.ws.entrepatas.security.UserPrincipal;
import app.ws.entrepatas.service.LocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequestMapping("api/local")
public class LocalController {

    @Autowired
    LocalService localService;


    @GetMapping("findAll")
    public List<LocalEntity> findAll(@RequestHeader(value="Authorization") String authorization){
        return localService.findAll();
    }

    @PostMapping("/create")
    public LocalEntity create(@RequestHeader(value="Authorization") String authorization, @RequestBody LocalEntity local, @ApiIgnore @CurrentUser UserPrincipal user) {
        return localService.create(local, user);
    }

    @PutMapping("/update")
    public LocalEntity update(@RequestHeader(value="Authorization") String authorization,@RequestBody LocalEntity local, @ApiIgnore @CurrentUser UserPrincipal user) {
        return localService.update(local, user);
    }
}
