package app.ws.entrepatas.controller;

import app.ws.entrepatas.dto.AdopcionDto;
import app.ws.entrepatas.dto.PublicacionDto;
import app.ws.entrepatas.model.AdopcionEntity;
import app.ws.entrepatas.service.AdopcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Page<AdopcionDto>> findAll(@RequestParam(name = "page") Integer page,
                                                        @RequestParam(name = "perPage") Integer perPage) {
        Pageable pageable = PageRequest.of(page, perPage);
        return ResponseEntity.ok().body(adopcionService.findAllAdopciones(pageable).map(AdopcionDto::transformToDto));
    }

    @GetMapping("/findAllDevoluciones")
    public ResponseEntity<Page<AdopcionDto>> findAllDevoluciones(@RequestParam(name = "page") Integer page,
                                                 @RequestParam(name = "perPage") Integer perPage) {
        Pageable pageable = PageRequest.of(page, perPage);
        return ResponseEntity.ok().body(adopcionService.findAllDevoluciones(pageable).map(AdopcionDto::transformToDto));
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
