package app.ws.entrepatas.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.util.Map;

public interface AmazonS3ClientService {

    ByteArrayOutputStream downloadFile(String keyName);

    Map<String, String> uploadFilePublic(MultipartFile multipartFile, String container);


}
