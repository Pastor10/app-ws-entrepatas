package app.ws.entrepatas.controller;

import app.ws.entrepatas.exception.NoExistEntityException;
import app.ws.entrepatas.model.TipoAnimalEntity;
import app.ws.entrepatas.service.TipoAnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tipoAnimal")
public class TipoAnimalController {

    @Autowired
    TipoAnimalService tipoAnimalService;

    @PostMapping("/create")
    public TipoAnimalEntity create(@RequestHeader(value="Authorization") String authorization,@RequestBody TipoAnimalEntity tipo) {
        return tipoAnimalService.create(tipo);
    }

    @GetMapping("/findAll")
    public List<TipoAnimalEntity> findAll(@RequestHeader(value="Authorization") String authorization) {
        return tipoAnimalService.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@RequestHeader(value="Authorization") String authorization,@PathVariable("id") Long id) {
        try {
            tipoAnimalService.delete(id);
        } catch (NoExistEntityException ex) {
            ex.printStackTrace();
        }
    }

    @PutMapping("/update")
    public TipoAnimalEntity update(@RequestHeader(value="Authorization") String authorization,@RequestBody TipoAnimalEntity tipo) throws Exception {
        return tipoAnimalService.update(tipo);
    }
}
