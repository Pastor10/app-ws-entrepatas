package app.ws.entrepatas.controller;

import app.ws.entrepatas.dto.PublicacionDto;
import app.ws.entrepatas.enums.ErrorCode;
import app.ws.entrepatas.exception.ServiceException;
import app.ws.entrepatas.model.PublicacionEntity;
import app.ws.entrepatas.security.CurrentUser;
import app.ws.entrepatas.security.UserPrincipal;
import app.ws.entrepatas.service.PublicacionService;
import app.ws.entrepatas.util.Constantes;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.io.File;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/publicacion")
public class PublicacionController {

    @Autowired
    PublicacionService publicacionService;

    @Autowired
    private AmazonS3 s3client;

    @Value("${app.aws.s3.bucket}")
    private String bucketName;

    @Value("${app.aws.s3.baseUrl}")
    private String baseUrl;


    @PostMapping("/create")
    public PublicacionDto create(@RequestHeader(value="Authorization") String authorization, @ApiIgnore @CurrentUser UserPrincipal user, @RequestBody PublicacionEntity publicacion) {
        return PublicacionDto.transformToDto(publicacionService.create(publicacion, user));

    }

    @GetMapping("/findAll")
    public List<PublicacionDto> findAll(@RequestHeader(value="Authorization") String authorization) {
        return PublicacionDto.transformToDto(publicacionService.findAll());
    }

    @GetMapping("/findAllAprobadas")
    public List<PublicacionDto> findAllAprobadas() {
        return PublicacionDto.transformToDtoAprobados(publicacionService.findAll());
    }

    @GetMapping("/findAllById/{id}")
    public List<PublicacionDto> findAllById(@RequestHeader(value="Authorization") String authorization,@PathVariable Long id) {
        return PublicacionDto.transformToDto(publicacionService.findAllById(id));
    }

    @PutMapping("/update")
    public PublicacionDto update(@RequestHeader(value="Authorization") String authorization, @ApiIgnore @CurrentUser UserPrincipal user,@RequestBody PublicacionEntity publicacion) throws Exception {
        return PublicacionDto.transformToDto(publicacionService.update(publicacion, user));
    }

    @GetMapping("/findById/{id}")
    public PublicacionDto findById(@RequestHeader(value="Authorization") String authorization,@PathVariable("id") Long id) {
        return PublicacionDto.transformToDto(publicacionService.findById(id));
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

    private MediaType contentType(String keyname) {
        String[] arr = keyname.split("\\.");
        String type = arr[arr.length - 1];
        switch (type) {
            case "txt":
                return MediaType.TEXT_PLAIN;
            case "png":
                return MediaType.IMAGE_PNG;
            case "jpg":
                return MediaType.IMAGE_JPEG;
            default:
                return MediaType.APPLICATION_OCTET_STREAM;
        }
    }
}
