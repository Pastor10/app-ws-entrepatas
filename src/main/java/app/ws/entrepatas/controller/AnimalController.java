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

    @GetMapping("/upload/img/{namePhoto:.+}")
    public ResponseEntity<Resource> showPhoto(@PathVariable String namePhoto) {

        Resource resource = null;
        try {
            String folder = Constantes.FILES_PUBLICACION;
            String url = String.format("%s/%s", this.baseUrl, folder);
            resource = new UrlResource(String.format("%s/%s", url, namePhoto));
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error with the directory of the file: ", e);
        }


        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"");

        return new ResponseEntity<Resource>(resource, header, HttpStatus.OK);
    }
}
