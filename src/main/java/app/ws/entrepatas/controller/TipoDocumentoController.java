package app.ws.entrepatas.controller;



import app.ws.entrepatas.model.TipoDocumentoEntity;
import app.ws.entrepatas.service.TipoDocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/tipoDocumento")
@CrossOrigin("*")
public class TipoDocumentoController {

    @Autowired
    TipoDocumentoService tipoDocumentoService;

    @GetMapping("/findAll")
    public List<TipoDocumentoEntity> findAll() {
        return tipoDocumentoService.findAll();
    }
}
