package app.ws.entrepatas.controller;

import app.ws.entrepatas.dto.AnimalDto;
import app.ws.entrepatas.model.AnimalEntity;
import app.ws.entrepatas.service.AnimalService;
import app.ws.entrepatas.util.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


@RestController
@RequestMapping("api/animal")
public class AnimalController {

    @Autowired
    AnimalService animalService;

    @Value("${app.aws.s3.baseUrl}")
    private String baseUrl;

    @GetMapping("/findAll")
    public List<AnimalDto> findAll() {
        return AnimalDto.transformToDto(animalService.findAll());
    }

    @GetMapping("/findById/{id}")
    public AnimalDto findById(@PathVariable("id") Long id) {
        return AnimalDto.transformToDto(animalService.findById(id).get());
    }

}
