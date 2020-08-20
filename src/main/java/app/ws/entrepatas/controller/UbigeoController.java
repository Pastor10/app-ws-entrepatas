package app.ws.entrepatas.controller;

import app.ws.entrepatas.model.UbigeoEntity;
import app.ws.entrepatas.service.UbigeoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/ubigeo")
@CrossOrigin("*")
public class UbigeoController {

    @Autowired
    UbigeoService ubigeoService;

    @GetMapping("/city")
    public ResponseEntity<?> city(@RequestParam String filter) {
        return ResponseEntity.ok().body(ubigeoService.searchCity(filter));
    }

    @GetMapping("/getCity")
    public UbigeoEntity getCity(@RequestParam String codDepartamento, @RequestParam String codProvincia, @RequestParam String codDistrito) {
        return ubigeoService.getCity(codDepartamento, codProvincia, codDistrito);
    }
}
