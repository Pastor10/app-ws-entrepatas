package app.ws.entrepatas.controller;

import app.ws.entrepatas.exception.NoExistEntityException;
import app.ws.entrepatas.model.TamanoAnimalEntity;
import app.ws.entrepatas.service.TamanoAnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/tamanoAnimal")
public class TamanoAnimalController {

    @Autowired
    TamanoAnimalService tamanoAnimalService;

    @PostMapping("/create")
    public TamanoAnimalEntity create(@RequestHeader(value="Authorization") String authorization,@RequestBody TamanoAnimalEntity tamano) {
        return tamanoAnimalService.create(tamano);
    }

    @GetMapping("/findAll")
    public List<TamanoAnimalEntity> findAll(@RequestHeader(value="Authorization") String authorization) {
        return tamanoAnimalService.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@RequestHeader(value="Authorization") String authorization,@PathVariable("id") Long id) {
        try {
            tamanoAnimalService.delete(id);
        } catch (NoExistEntityException ex) {
            ex.printStackTrace();
        }
    }

    @PutMapping("/update")
    public TamanoAnimalEntity update(@RequestHeader(value="Authorization") String authorization,@RequestBody TamanoAnimalEntity tamano) throws Exception {
        return tamanoAnimalService.update(tamano);
    }
}
