package app.ws.entrepatas.controller;

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
    public TipoEventoEntity create(@RequestHeader(value="Authorization") String authorization,@RequestParam("id") String id,@RequestParam("nombre") String nombre, @RequestParam("file") MultipartFile foto) {
        TipoEventoEntity tipoEvento = new TipoEventoEntity();
        Long idEvento = null;
        if (!id.equalsIgnoreCase("0")){
            idEvento = Long.parseLong(id);
        }

        if (!foto.isEmpty()){
            String fileName = foto.getOriginalFilename();
            String fileNameKey = String.format("%s-%s", UUID.randomUUID(), fileName);
            String folder = Constantes.FILES_TIPO_EVENTO;
            String bucket = String.format("%s/%s", this.bucketName, folder);

            try {
                tipoEvento.setId(idEvento);
                tipoEvento.setNombre(nombre);
                tipoEvento.setEstado(Boolean.TRUE);
                tipoEvento.setImagen(fileNameKey);

                //creating the file in the server (temporarily)

                File file = new File(fileName);
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(foto.getBytes());
                fos.close();

                PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, fileNameKey, file);
                putObjectRequest.withCannedAcl(CannedAccessControlList.PublicRead);

                this.s3client.putObject(putObjectRequest);
                //removing the file created in the server
                file.delete();



            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return tipoEventoService.create(tipoEvento);

    }

    @GetMapping("findAll")
    public List<TipoEventoEntity> findAll(@RequestHeader(value="Authorization") String authorization){
        return tipoEventoService.findAll();
    }

    @GetMapping("/upload/img/{namePhoto:.+}")
    public ResponseEntity<Resource> showPhoto(@PathVariable String namePhoto) {
        Resource resource = null;
        try {
            String folder = Constantes.FILES_TIPO_EVENTO;
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
