package app.ws.entrepatas.controller;

import app.ws.entrepatas.dto.PublicacionDto;
import app.ws.entrepatas.enums.CondicionAnimal;
import app.ws.entrepatas.model.PublicacionEntity;
import app.ws.entrepatas.security.CurrentUser;
import app.ws.entrepatas.security.UserPrincipal;
import app.ws.entrepatas.service.PublicacionService;
import app.ws.entrepatas.util.UtilDate;
import com.amazonaws.services.s3.AmazonS3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/publicacion")
@CrossOrigin("*")
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
    public ResponseEntity<Page<PublicacionDto>> findAll(@RequestHeader(value="Authorization") String authorization,
                                                        @RequestParam(name = "page") Integer page,
                                                        @RequestParam(name = "perPage") Integer perPage,
                                                        @RequestParam(name = "desde", required = false) String desde,
                                                        @RequestParam(name = "hasta", required = false) String hasta,
                                                        @RequestParam(name = "condicion", required = false) List<CondicionAnimal> condicion) {
        Pageable pageable = PageRequest.of(page, perPage);
        LocalDate filtroDesde = null;
        LocalDate filtroHasta = null;
        if (desde != null) filtroDesde = UtilDate.stringToLocalDate(desde, "dd-MM-yyyy");
        if (hasta != null) filtroHasta = UtilDate.stringToLocalDate(hasta, "dd-MM-yyyy");
        return ResponseEntity.ok().body(publicacionService.findAll(filtroDesde, filtroHasta, condicion,pageable).map(PublicacionDto::transformToDto));
    }

    @GetMapping("/findAll-visitantes")
    public ResponseEntity<Page<PublicacionDto>> findAllVisitantes(@RequestHeader(value="Authorization") String authorization,
                                                        @RequestParam(name = "page") Integer page,
                                                        @RequestParam(name = "perPage") Integer perPage) {
        Pageable pageable = PageRequest.of(page, perPage);
        return ResponseEntity.ok().body(publicacionService.findAllVisitantes(pageable).map(PublicacionDto::transformToDto));
    }


    @GetMapping("/findAllAprobadas")
    public List<PublicacionDto> findAllAprobadas() {
        return PublicacionDto.transformToDtoAprobados(publicacionService.findAllPublicaciones());
    }

    @GetMapping("/publicacion-adopcion")
    public List<PublicacionDto> findAllCondicionAdopcion(@RequestHeader(value="Authorization") String authorization) {
        return PublicacionDto.transformToDtoCondicionAdopcion(publicacionService.findAllPublicaciones());
    }

    @GetMapping("/findAllById/{id}")
    public List<PublicacionDto>findAllById(@RequestHeader(value="Authorization") String authorization,
                                            @PathVariable(name = "id") Long id) {
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

}
