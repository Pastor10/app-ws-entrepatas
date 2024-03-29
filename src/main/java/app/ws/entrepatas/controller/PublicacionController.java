package app.ws.entrepatas.controller;

import app.ws.entrepatas.dto.PublicacionDto;
import app.ws.entrepatas.enums.CondicionAnimal;
import app.ws.entrepatas.model.PublicacionEntity;
import app.ws.entrepatas.security.CurrentUser;
import app.ws.entrepatas.security.UserPrincipal;
import app.ws.entrepatas.service.PublicacionService;
import app.ws.entrepatas.util.UtilDate;
import com.amazonaws.services.s3.AmazonS3;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/publicacion")
@CrossOrigin("*")
public class PublicacionController {

    @Autowired
    PublicacionService publicacionService;



    @PostMapping("/create")
    @ApiOperation(value = "creacion de publicacion")
    public PublicacionDto create(@RequestHeader(value="Authorization") String authorization, @ApiIgnore @CurrentUser UserPrincipal user, @RequestBody PublicacionEntity publicacion) {
        return PublicacionDto.transformToDto(publicacionService.create(publicacion, user));

    }

    @PreAuthorize("hasAuthority('ROLE_PUBLICACION_LISTADO')")
    @GetMapping("/findAll")
    @ApiOperation(value = "lista de todas las publicaciones creadas")
    public ResponseEntity<Page<PublicacionDto>> findAll(@RequestHeader(value="Authorization") String authorization,
                                                        @RequestParam(name = "page") Integer page,
                                                        @RequestParam(name = "perPage") Integer perPage,
                                                        @RequestParam(name = "desde", required = false) String desde,
                                                        @RequestParam(name = "hasta", required = false) String hasta,
                                                        @RequestParam(name = "condicion", required = false) List<CondicionAnimal> condicion) {
        Pageable pageable = PageRequest.of(page, perPage);
        LocalDate filtroDesde = null;
        LocalDate filtroHasta = null;
        if (desde != null) filtroDesde = UtilDate.stringToLocalDate(desde, "dd-MM-yyyy");
        if (hasta != null) filtroHasta = UtilDate.stringToLocalDate(hasta, "dd-MM-yyyy");
        return ResponseEntity.ok().body(publicacionService.findAll(filtroDesde, filtroHasta, condicion,pageable).map(PublicacionDto::transformToDto));
    }

    @PreAuthorize("hasAuthority('ROLE_PUBLICACION_APROBACION')")
    @GetMapping("/findAll-visitantes")
    @ApiOperation(value = "lista de todas las publicaciones de los visitantes creadas")
    public ResponseEntity<Page<PublicacionDto>> findAllVisitantes(@RequestHeader(value="Authorization") String authorization,
                                                        @RequestParam(name = "page") Integer page,
                                                        @RequestParam(name = "perPage") Integer perPage) {
        Pageable pageable = PageRequest.of(page, perPage);
        return ResponseEntity.ok().body(publicacionService.findAllVisitantes(pageable).map(PublicacionDto::transformToDto));
    }


    @GetMapping("/findAllAprobadas")
    @ApiOperation(value = " api publica de todas las publicaciones aprobadas")
    public List<PublicacionDto> findAllAprobadas() {
        return PublicacionDto.transformToDtoAprobados(publicacionService.findAllPublicaciones());
    }

    @PreAuthorize("hasAuthority('ROLE_ADOPCION_POSTULANTE_LISTADO')")
    @GetMapping("/publicacion-adopcion")
    @ApiOperation(value = " lista de las publicaciones en condicion de adopcion")
    public List<PublicacionDto> findAllCondicionAdopcion(@RequestHeader(value="Authorization") String authorization) {
        return PublicacionDto.transformToDtoCondicionAdopcion(publicacionService.findAllPublicaciones());
    }


    @GetMapping("/findAllById/{id}")
    @ApiOperation(value = " busqueda de todas las publicaciones por id usuario")
    public List<PublicacionDto>findAllById(@RequestHeader(value="Authorization") String authorization,
                                            @PathVariable(name = "id") Long id) {
        return PublicacionDto.transformToDto(publicacionService.findAllById(id));
    }


    @PutMapping("/update")
    @ApiOperation(value = " actualizar publicacion")
    public PublicacionDto update(@RequestHeader(value="Authorization") String authorization, @ApiIgnore @CurrentUser UserPrincipal user,@RequestBody PublicacionEntity publicacion) throws Exception {
        return PublicacionDto.transformToDto(publicacionService.update(publicacion, user));
    }


    @GetMapping("/findById/{id}")
    @ApiOperation(value = " buscar  publicacion por id")
    public PublicacionDto findById(@RequestHeader(value="Authorization") String authorization,@PathVariable("id") Long id) {
        return PublicacionDto.transformToDto(publicacionService.findById(id));
    }

}
