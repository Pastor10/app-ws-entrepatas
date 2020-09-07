package app.ws.entrepatas.controller;

import app.ws.entrepatas.dto.AdopcionDto;
import app.ws.entrepatas.model.AdopcionEntity;
import app.ws.entrepatas.security.CurrentUser;
import app.ws.entrepatas.security.UserPrincipal;
import app.ws.entrepatas.service.AdopcionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("api/adopcion")
@CrossOrigin("*")
public class AdopcionController {

    @Autowired
    AdopcionService adopcionService;

    @PreAuthorize("hasAuthority('ROLE_ADOPCION_GENERAR')")
    @PostMapping("/create")
    @ApiOperation(value = "reserva adopcion a un postulante")
    public AdopcionEntity create(@RequestHeader(value="Authorization") String authorization,@RequestBody AdopcionEntity adopcion,  @ApiIgnore @CurrentUser UserPrincipal user) {
        return adopcionService.create(adopcion, user);
    }

    @PreAuthorize("hasAuthority('ROLE_ADOPCION_LISTADO')")
    @GetMapping("/findAll")
    @ApiOperation(value = "lista todas las adopciones registradas")
    public ResponseEntity<Page<AdopcionDto>> findAll(@RequestHeader(value="Authorization") String authorization,@RequestParam(name = "page") Integer page,
                                                        @RequestParam(name = "perPage") Integer perPage) {
        Pageable pageable = PageRequest.of(page, perPage);
        return ResponseEntity.ok().body(adopcionService.findAllAdopciones(pageable).map(AdopcionDto::transformToDto));
    }

    @PreAuthorize("hasAuthority('ROLE_ADOPCION_DEVOLUCION')")
    @GetMapping("/findAllDevoluciones")
    @ApiOperation(value = "lista todas las devoluciones registradas")
    public ResponseEntity<Page<AdopcionDto>> findAllDevoluciones(@RequestHeader(value="Authorization") String authorization,@RequestParam(name = "page") Integer page,
                                                 @RequestParam(name = "perPage") Integer perPage) {
        Pageable pageable = PageRequest.of(page, perPage);
        return ResponseEntity.ok().body(adopcionService.findAllDevoluciones(pageable).map(AdopcionDto::transformToDto));
    }



    @PreAuthorize("hasAuthority('ROLE_ADOPCION_GENERAR')")
    @PutMapping("/update")
    @ApiOperation(value = "actualiza una adopcion")
    public AdopcionDto update(@RequestHeader(value="Authorization") String authorization,@RequestBody AdopcionEntity adopcion) {
        return AdopcionDto.transformToDto(adopcionService.update(adopcion));
    }

    @PreAuthorize("hasAuthority('ROLE_ADOPCION_GENERAR')")
    @GetMapping("/findById/{id}")
    @ApiOperation(value = "busca adopcion por id")
    public AdopcionDto getAdopcionById(@RequestHeader(value="Authorization") String authorization,@PathVariable("id") Long id) {
        return AdopcionDto.transformToDto(adopcionService.findById(id));
    }
}
