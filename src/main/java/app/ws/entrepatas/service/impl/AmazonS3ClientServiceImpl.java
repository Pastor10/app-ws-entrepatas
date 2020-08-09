package app.ws.entrepatas.service.impl;

import app.ws.entrepatas.enums.ErrorCode;
import app.ws.entrepatas.service.AmazonS3ClientService;
import app.ws.entrepatas.util.Constantes;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
public class AmazonS3ClientServiceImpl implements AmazonS3ClientService {

    @Autowired
    private AmazonS3 s3client;

    @Value("${app.aws.s3.bucket}")
    private String bucketName;

    @Value("${app.aws.s3.baseUrl}")
    private String baseUrl;

    @Override
    public ByteArrayOutputStream downloadFile(String keyName) {
        return getFile(this.bucketName,keyName);
    }

    public ByteArrayOutputStream getFile(String bucketName, String keyName) {
        try {

            StopWatch watch = new StopWatch();
            watch.start();

            S3Object s3object = this.s3client.getObject(new GetObjectRequest(bucketName, keyName));

            InputStream is = s3object.getObjectContent();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int len;
            byte[] buffer = new byte[4096];
            while ((len = is.read(buffer, 0, buffer.length)) != -1) {
                baos.write(buffer, 0, len);
            }

            watch.stop();
            log.info(String.format("%s %s %s", "Tiempo de Descarga de archivo:", keyName, watch.getTime()));

            return baos;
        } catch (Exception ioe) {
            log.error("Exception: " + ioe.getMessage());
            throw new ServiceException("El servicio de storage está fuera de servicio, comuniquese con el administrador.");
        }
    }

    @Override
    public Map<String, String> uploadFilePublic(MultipartFile multipartFile, String container) {
        String fileName = multipartFile.getOriginalFilename();
        String fileNameKey = String.format("%s-%s", UUID.randomUUID(), fileName);
        String bucket = String.format("%s/%s", this.bucketName,  container);

        try {
            //creating the file in the server (temporarily)
            File file = new File(fileName);
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(multipartFile.getBytes());
            fos.close();

            PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, fileNameKey, file);
            putObjectRequest.withCannedAcl(CannedAccessControlList.PublicRead);

            this.s3client.putObject(putObjectRequest);
            //removing the file created in the server
            file.delete();

            Map<String, String> resp = new HashMap<>();
            resp.put("url", String.format("%s/%s/%s", baseUrl, container, fileNameKey));
            return resp;


        } catch (IOException | AmazonServiceException ex) {
            log.error("error [" + ex.getMessage() + "] occurred while uploading [" + fileName + "] ");
            throw new app.ws.entrepatas.exception.ServiceException(ErrorCode.V013);
        }
    }
}
