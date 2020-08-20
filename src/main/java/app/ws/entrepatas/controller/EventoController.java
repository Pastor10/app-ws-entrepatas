package app.ws.entrepatas.controller;

import app.ws.entrepatas.dto.EventoDto;
import app.ws.entrepatas.model.EventoEntity;
import app.ws.entrepatas.security.CurrentUser;
import app.ws.entrepatas.security.UserPrincipal;
import app.ws.entrepatas.service.AmazonS3ClientService;
import app.ws.entrepatas.service.EventoService;
import app.ws.entrepatas.util.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @PostMapping("/create")
    public EventoEntity create(@RequestHeader(value="Authorization") String authorization, @RequestBody EventoEntity evento, @ApiIgnore @CurrentUser UserPrincipal user) {
        return eventoService.create(evento, user);
    }

    @GetMapping("findAll")
    public List<EventoDto> findAll(){
        return EventoDto.transformToDto(eventoService.findAll());
    }

    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> uploadFsPublic(@RequestHeader(value="Authorization") String authorization,@RequestPart(value = "file") MultipartFile file) {
        return ResponseEntity.ok().body(amazonS3ClientService.uploadFilePublic(file, Constantes.FILES_EVENTOS));

    }

    @PutMapping("/update")
    public EventoEntity update(@RequestHeader(value="Authorization") String authorization, @RequestBody EventoEntity evento, @ApiIgnore @CurrentUser UserPrincipal user) {
        return eventoService.update(evento, user);
    }

    @GetMapping("/findById/{id}")
    public EventoEntity findById(@RequestHeader(value="Authorization") String authorization, @PathVariable("id") Long id) {
        return eventoService.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@RequestHeader(value="Authorization") String authorization,@PathVariable("id") Long id,@ApiIgnore @CurrentUser UserPrincipal user) {
        eventoService.delete(id, user);

    }


}
