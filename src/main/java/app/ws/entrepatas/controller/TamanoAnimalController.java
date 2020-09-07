package app.ws.entrepatas.controller;

import app.ws.entrepatas.exception.NoExistEntityException;
import app.ws.entrepatas.model.TamanoAnimalEntity;
import app.ws.entrepatas.service.TamanoAnimalService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/tamanoAnimal")
@CrossOrigin("*")
public class TamanoAnimalController {

    @Autowired
    TamanoAnimalService tamanoAnimalService;

    @PostMapping("/create")
    @ApiOperation(value = "creacion tamano animal")
    public TamanoAnimalEntity create(@RequestHeader(value="Authorization") String authorization,@RequestBody TamanoAnimalEntity tamano) {
        return tamanoAnimalService.create(tamano);
    }

    @GetMapping("/findAll")
    @ApiOperation(value = "listado tamano animal")
    public List<TamanoAnimalEntity> findAll(@RequestHeader(value="Authorization") String authorization) {
        return tamanoAnimalService.findAll();
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "eliminar tamano animal por id")
    public void delete(@RequestHeader(value="Authorization") String authorization,@PathVariable("id") Long id) {
        try {
            tamanoAnimalService.delete(id);
        } catch (NoExistEntityException ex) {
            ex.printStackTrace();
        }
    }

    @PutMapping("/update")
    @ApiOperation(value = "actualizar datos tamano animal")
    public TamanoAnimalEntity update(@RequestHeader(value="Authorization") String authorization,@RequestBody TamanoAnimalEntity tamano) throws Exception {
        return tamanoAnimalService.update(tamano);
    }
}
