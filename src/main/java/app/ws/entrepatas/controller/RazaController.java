package app.ws.entrepatas.controller;

import app.ws.entrepatas.exception.NoExistEntityException;
import app.ws.entrepatas.model.RazaEntity;
import app.ws.entrepatas.service.RazaService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/raza")
@CrossOrigin("*")
public class RazaController {

    @Autowired
    RazaService razaService;

    @PreAuthorize("hasAuthority('ROLE_ANIMAL_RAZA')")
    @PostMapping("/create")
    @ApiOperation(value = "creacion de raza")
    public RazaEntity create(@RequestHeader(value="Authorization") String authorization,@RequestBody RazaEntity raza) {
        return razaService.create(raza);
    }

    @PreAuthorize("hasAuthority('ROLE_ANIMAL_RAZA')")
    @GetMapping("/findAll")
    @ApiOperation(value = "listado de razas")
    public ResponseEntity<Page<RazaEntity>> findAll(@RequestHeader(value="Authorization") String authorization,
                                                    @RequestParam(name = "page") Integer page,
                                                    @RequestParam(name = "perPage") Integer perPage) {
        Pageable pageable = PageRequest.of(page, perPage);
        return ResponseEntity.ok().body(razaService.findAll(pageable));
    }

    @PreAuthorize("hasAuthority('ROLE_ANIMAL_RAZA')")
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "eliminar raza por id")
    public void delete(@RequestHeader(value="Authorization") String authorization,@PathVariable("id") Long id) {
        try {
            razaService.delete(id);
        } catch (NoExistEntityException ex) {
            ex.printStackTrace();
        }
    }

    @PreAuthorize("hasAuthority('ROLE_ANIMAL_RAZA')")
    @PutMapping("/update")
    @ApiOperation(value = "actualizar datos de una raza")
    public RazaEntity update(@RequestHeader(value="Authorization") String authorization,@RequestBody RazaEntity raza) throws Exception {
        return razaService.update(raza);
    }


    @GetMapping("/findById/{id}")
    @ApiOperation(value = "buscar una raza por id")
    public RazaEntity findById(@RequestHeader(value="Authorization") String authorization,@PathVariable("id") Long id) {
        return razaService.findById(id).get();
    }

    @GetMapping("/findAllById/{id}")
    @ApiOperation(value = "listar razas por tipo animal")
    public List<RazaEntity> findByAllId(@RequestHeader(value="Authorization") String authorization,@PathVariable("id") Long id) {
        return  razaService.findAllById(id);
    }
}
