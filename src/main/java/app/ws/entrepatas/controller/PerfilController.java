package app.ws.entrepatas.controller;

import app.ws.entrepatas.exception.NoExistEntityException;
import app.ws.entrepatas.model.PerfilEntity;
import app.ws.entrepatas.service.PerfilService;
import app.ws.entrepatas.util.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("api/perfil")
//@PreAuthorize("hasRole('PERFIL')")
public class PerfilController {

    @Autowired
    private PerfilService perfilService;

    @PostMapping("/create")
    public PerfilEntity create(@RequestBody @Valid PerfilEntity Perfil) {
        return perfilService.create(Perfil);
    }

    @PutMapping("/update")
    public PerfilEntity update(@RequestBody @Valid PerfilEntity Perfil) throws Exception {
        return perfilService.update(Perfil);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        try {
            perfilService.delete(id);
        } catch (NoExistEntityException ex) {

        }
    }

    @GetMapping("/findAll")
    public List<PerfilEntity> findAll() {
        return perfilService.findAll();
    }

    @GetMapping("/findById/{id}")
    public PerfilEntity findById(@PathVariable("id") Long id) {
        return perfilService.findById(id).get();
    }

    @GetMapping(value = {"/findByName", "/findByName/{name}"})
    public List<PerfilEntity> findByName(@PathVariable("name") Optional<String> name) {
        if (name.isPresent()) {
            return perfilService.findByNombreIsContainingIgnoreCase(name.get().trim());
        } else {
            return perfilService.findAll();
        }
    }


}
