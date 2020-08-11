package app.ws.entrepatas.controller;

import app.ws.entrepatas.exception.NoExistEntityException;
import app.ws.entrepatas.model.EventoEntity;
import app.ws.entrepatas.model.TipoEventoEntity;
import app.ws.entrepatas.model.UsuarioEntity;
import app.ws.entrepatas.service.TipoEventoService;
import app.ws.entrepatas.util.Constantes;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/tipoEvento")
public class TipoEventoController {

    @Autowired
    TipoEventoService tipoEventoService;

    @Autowired
    private AmazonS3 s3client;

    @Value("${app.aws.s3.bucket}")
    private String bucketName;

    @Value("${app.aws.s3.baseUrl}")
    private String baseUrl;


    @PostMapping("/create")
    public TipoEventoEntity create(@RequestHeader(value="Authorization") String authorization, @RequestBody TipoEventoEntity tipoEvento) {
        return tipoEventoService.create(tipoEvento);

    }

    @GetMapping("findAll")
    public List<TipoEventoEntity> findAll(@RequestHeader(value="Authorization") String authorization){
        return tipoEventoService.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@RequestHeader(value="Authorization") String authorization,@PathVariable("id") Long id) {
        try {
            tipoEventoService.delete(id);
        } catch (NoExistEntityException ex) {
            ex.printStackTrace();
        }
    }



}
