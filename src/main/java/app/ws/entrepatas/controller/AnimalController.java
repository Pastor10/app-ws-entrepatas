package app.ws.entrepatas.controller;

import app.ws.entrepatas.dto.AnimalDto;
import app.ws.entrepatas.service.AnimalService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("api/animal")
@CrossOrigin("*")
public class AnimalController {

    @Autowired
    AnimalService animalService;



    @GetMapping("/findAll")
    @ApiOperation(value = "listamos todos los animales")
    public List<AnimalDto> findAll(@RequestHeader(value="Authorization") String authorization) {
        return AnimalDto.transformToDto(animalService.findAll());
    }

    @GetMapping("/findById/{id}")
    @ApiOperation(value = "buscamos un animal por id")
    public AnimalDto findById(@RequestHeader(value="Authorization") String authorization,@PathVariable("id") Long id) {
        return AnimalDto.transformToDto(animalService.findById(id).get());
    }

}
