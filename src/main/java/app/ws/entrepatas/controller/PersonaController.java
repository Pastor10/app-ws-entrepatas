package app.ws.entrepatas.controller;

import app.ws.entrepatas.dto.PersonaDto;
import app.ws.entrepatas.model.PersonaEntity;
import app.ws.entrepatas.service.AmazonS3ClientService;
import app.ws.entrepatas.service.PersonaService;
import app.ws.entrepatas.util.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("api/persona")
@CrossOrigin("*")
public class PersonaController {

    @Autowired
    PersonaService personaService;

    @Autowired
    private AmazonS3ClientService amazonS3ClientService;

    @GetMapping("/findByDocumento")
    public PersonaDto findByDocumento(@RequestParam String documento) {
        return PersonaDto.transformToDto(personaService.findByDocumento(documento));
    }

    @PutMapping("/update")
    public PersonaDto update(@RequestHeader(value="Authorization") String authorization,@RequestBody PersonaEntity persona) {
        return PersonaDto.transformToDto(personaService.update(persona));
    }

    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> uploadFsPublic(@RequestHeader(value="Authorization") String authorization,@RequestPart(value = "file") MultipartFile file) {
        return ResponseEntity.ok().body(amazonS3ClientService.uploadFilePublic(file, Constantes.FILES_USERS));

    }

}
