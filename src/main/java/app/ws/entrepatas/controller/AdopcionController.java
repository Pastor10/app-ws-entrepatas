package app.ws.entrepatas.controller;

import app.ws.entrepatas.dto.AdopcionDto;
import app.ws.entrepatas.model.AdopcionEntity;
import app.ws.entrepatas.service.AdopcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/adopcion")
@CrossOrigin("*")
public class AdopcionController {

    @Autowired
    AdopcionService adopcionService;

    @PostMapping("/create")
    public AdopcionEntity create(@RequestHeader(value="Authorization") String authorization,@RequestBody AdopcionEntity adopcion) {
        return adopcionService.create(adopcion);
    }

    @GetMapping("/findAll")
    public ResponseEntity<Page<AdopcionDto>> findAll(@RequestHeader(value="Authorization") String authorization,@RequestParam(name = "page") Integer page,
                                                        @RequestParam(name = "perPage") Integer perPage) {
        Pageable pageable = PageRequest.of(page, perPage);
        return ResponseEntity.ok().body(adopcionService.findAllAdopciones(pageable).map(AdopcionDto::transformToDto));
    }

    @GetMapping("/findAllDevoluciones")
    public ResponseEntity<Page<AdopcionDto>> findAllDevoluciones(@RequestHeader(value="Authorization") String authorization,@RequestParam(name = "page") Integer page,
                                                 @RequestParam(name = "perPage") Integer perPage) {
        Pageable pageable = PageRequest.of(page, perPage);
        return ResponseEntity.ok().body(adopcionService.findAllDevoluciones(pageable).map(AdopcionDto::transformToDto));
    }



    @PutMapping("/update")
    public AdopcionDto update(@RequestHeader(value="Authorization") String authorization,@RequestBody AdopcionEntity adopcion) {
        return AdopcionDto.transformToDto(adopcionService.update(adopcion));
    }

    @GetMapping("/findById/{id}")
    public AdopcionDto getAdopcionById(@RequestHeader(value="Authorization") String authorization,@PathVariable("id") Long id) {
        return AdopcionDto.transformToDto(adopcionService.findById(id));
    }
}
