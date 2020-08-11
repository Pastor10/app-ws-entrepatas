package app.ws.entrepatas.controller;

import app.ws.entrepatas.dto.AdopcionDto;
import app.ws.entrepatas.dto.PublicacionDto;
import app.ws.entrepatas.model.AdopcionEntity;
import app.ws.entrepatas.service.AdopcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/adopcion")
public class AdopcionController {

    @Autowired
    AdopcionService adopcionService;

    @PostMapping("/create")
    public AdopcionEntity create(@RequestBody AdopcionEntity adopcion) {
        return adopcionService.create(adopcion);
    }

    @GetMapping("/findAll")
    public List<AdopcionDto> findAll() {
        return AdopcionDto.transformToDto(adopcionService.findAllAdopciones());
    }

    @GetMapping("/findAllDevoluciones")
    public List<AdopcionDto> findAllDevoluciones() {
        return AdopcionDto.transformToDto(adopcionService.findAllDevoluciones());
    }



    @PutMapping("/update")
    public AdopcionDto update(@RequestBody AdopcionEntity adopcion) {
        return AdopcionDto.transformToDto(adopcionService.update(adopcion));
    }

    @GetMapping("/findById/{id}")
    public AdopcionDto getAdopcionById(@PathVariable("id") Long id) {
        return AdopcionDto.transformToDto(adopcionService.findById(id));
    }
}
