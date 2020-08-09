package app.ws.entrepatas.controller;

import app.ws.entrepatas.service.AmazonS3ClientService;
import app.ws.entrepatas.util.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.util.Map;

@RestController
@RequestMapping("api/file")
public class FileController {

    @Autowired
    private AmazonS3ClientService amazonS3ClientService;

    @GetMapping("/name/{keyname}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String keyname) {
        ByteArrayOutputStream downloadInputStream = amazonS3ClientService.downloadFile(keyname);

        return ResponseEntity.ok()
                .contentType(contentType(keyname))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + keyname + "\"")
                .body(downloadInputStream.toByteArray());
    }

    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> uploadFsPublic(@RequestPart(value = "file") MultipartFile file) {
        return ResponseEntity.ok().body(amazonS3ClientService.uploadFilePublic(file, Constantes.FILES_PUBLICACION));

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
