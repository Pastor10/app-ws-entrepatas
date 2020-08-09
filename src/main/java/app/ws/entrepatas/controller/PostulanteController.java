package app.ws.entrepatas.controller;

import app.ws.entrepatas.dto.PostulanteDto;
import app.ws.entrepatas.dto.PublicacionDto;
import app.ws.entrepatas.model.LocalEntity;
import app.ws.entrepatas.model.PostulanteEntity;
import app.ws.entrepatas.model.PublicacionEntity;
import app.ws.entrepatas.model.UsuarioEntity;
import app.ws.entrepatas.security.CurrentUser;
import app.ws.entrepatas.security.UserPrincipal;
import app.ws.entrepatas.service.PostulanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;


@RestController
@RequestMapping("api/postulante")
public class PostulanteController {

    @Autowired
    PostulanteService postulanteService;

    @PostMapping("/create")
    public PostulanteDto create(@RequestBody PostulanteEntity postulante) {
        return PostulanteDto.transformToDto(postulanteService.create(postulante));
    }

    @GetMapping("/findAllPublicacion/{id}")
    public List<PostulanteDto> findAllPublicacion(@PathVariable("id") Long idPublicacion) {
        return PostulanteDto.transformToDto(postulanteService.findAllByPublicacion(idPublicacion));
    }

    @PutMapping("/update")
    public PostulanteDto update(@RequestHeader(value="Authorization") String authorization,  @ApiIgnore @CurrentUser UserPrincipal user, @RequestBody PostulanteEntity postulante) {
        return PostulanteDto.transformToDto(postulanteService.update(postulante, user));
    }

}
