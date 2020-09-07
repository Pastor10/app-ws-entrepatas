package app.ws.entrepatas.controller;

import app.ws.entrepatas.dto.EventoDto;
import app.ws.entrepatas.model.EventoEntity;
import app.ws.entrepatas.security.CurrentUser;
import app.ws.entrepatas.security.UserPrincipal;
import app.ws.entrepatas.service.AmazonS3ClientService;
import app.ws.entrepatas.service.EventoService;
import app.ws.entrepatas.util.Constantes;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/evento")
@CrossOrigin("*")
public class EventoController {

    @Autowired
    EventoService eventoService;

    @Autowired
    private AmazonS3ClientService amazonS3ClientService;

    @PreAuthorize("hasAuthority('ROLE_EVENTO_GENERA')")
    @PostMapping("/create")
    @ApiOperation(value = "creacion de eventos")
    public EventoEntity create(@RequestHeader(value="Authorization") String authorization, @RequestBody EventoEntity evento, @ApiIgnore @CurrentUser UserPrincipal user) {
        return eventoService.create(evento, user);
    }

    @PreAuthorize("hasAuthority('ROLE_EVENTO_LISTADO')")
    @GetMapping("findAll")
    @ApiOperation(value = "listado de todos los eventos creados")
    public ResponseEntity<Page<EventoEntity>> findAll(@RequestHeader(value="Authorization") String authorization,
                                                    @RequestParam(name = "page") Integer page,
                                                    @RequestParam(name = "perPage") Integer perPage){
        Pageable pageable = PageRequest.of(page, perPage);
        return ResponseEntity.ok().body(eventoService.findAll(pageable));
    }

    @GetMapping("proximos-eventos")
    @ApiOperation(value = "api publica de listado de todos los eventos proximps")
    public List<EventoDto> findAllProximos(){
        return EventoDto.transformToDtoProximos(eventoService.findAllProximos());
    }

    @PreAuthorize("hasAuthority('ROLE_EVENTO_GENERA')")
    @PostMapping("/upload")
    @ApiOperation(value = "subir una imagen de evento")
    public ResponseEntity<Map<String, String>> uploadFsPublic(@RequestHeader(value="Authorization") String authorization,@RequestPart(value = "file") MultipartFile file) {
        return ResponseEntity.ok().body(amazonS3ClientService.uploadFilePublic(file, Constantes.FILES_EVENTOS));

    }

    @PreAuthorize("hasAuthority('ROLE_EVENTO_GENERA')")
    @PutMapping("/update")
    @ApiOperation(value = "actualizar datos de evento")
    public EventoEntity update(@RequestHeader(value="Authorization") String authorization, @RequestBody EventoEntity evento, @ApiIgnore @CurrentUser UserPrincipal user) {
        return eventoService.update(evento, user);
    }

    @PreAuthorize("hasAuthority('ROLE_EVENTO_GENERA')")
    @GetMapping("/findById/{id}")
    @ApiOperation(value = "buscar un evento por id")
    public EventoEntity findById(@RequestHeader(value="Authorization") String authorization, @PathVariable("id") Long id) {
        return eventoService.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "eliminar un evento por id")
    public void delete(@RequestHeader(value="Authorization") String authorization,@PathVariable("id") Long id,@ApiIgnore @CurrentUser UserPrincipal user) {
        eventoService.delete(id, user);

    }


}
