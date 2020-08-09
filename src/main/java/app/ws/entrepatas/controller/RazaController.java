package app.ws.entrepatas.controller;

import app.ws.entrepatas.exception.NoExistEntityException;
import app.ws.entrepatas.model.RazaEntity;
import app.ws.entrepatas.model.UsuarioEntity;
import app.ws.entrepatas.service.RazaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/raza")
public class RazaController {

    @Autowired
    RazaService razaService;

    @PostMapping("/create")
    public RazaEntity create(@RequestHeader(value="Authorization") String authorization,@RequestBody RazaEntity raza) {
        return razaService.create(raza);
    }

    @GetMapping("/findAll")
    public List<RazaEntity> findAll(@RequestHeader(value="Authorization") String authorization) {
        return razaService.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@RequestHeader(value="Authorization") String authorization,@PathVariable("id") Long id) {
        try {
            razaService.delete(id);
        } catch (NoExistEntityException ex) {
            ex.printStackTrace();
        }
    }

    @PutMapping("/update")
    public RazaEntity update(@RequestHeader(value="Authorization") String authorization,@RequestBody RazaEntity raza) throws Exception {
        return razaService.update(raza);
    }

    @GetMapping("/findById/{id}")
    public RazaEntity findById(@PathVariable("id") Long id) {
        return razaService.findById(id).get();
    }

    @GetMapping("/findAllById/{id}")
    public List<RazaEntity> findByAllId(@PathVariable("id") Long id) {
        List<RazaEntity> listRazas= razaService.findAllById(id);
        return listRazas;
    }
}
