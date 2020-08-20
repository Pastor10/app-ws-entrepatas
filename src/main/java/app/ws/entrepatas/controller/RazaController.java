package app.ws.entrepatas.controller;

import app.ws.entrepatas.exception.NoExistEntityException;
import app.ws.entrepatas.model.RazaEntity;
import app.ws.entrepatas.service.RazaService;
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

import java.util.List;

@RestController
@RequestMapping("api/raza")
@CrossOrigin("*")
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
    public RazaEntity findById(@RequestHeader(value="Authorization") String authorization,@PathVariable("id") Long id) {
        return razaService.findById(id).get();
    }

    @GetMapping("/findAllById/{id}")
    public List<RazaEntity> findByAllId(@RequestHeader(value="Authorization") String authorization,@PathVariable("id") Long id) {
        return  razaService.findAllById(id);
    }
}
