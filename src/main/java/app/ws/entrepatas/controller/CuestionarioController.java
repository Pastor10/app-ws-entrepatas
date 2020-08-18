package app.ws.entrepatas.controller;

import app.ws.entrepatas.model.CuestionarioEntity;
import app.ws.entrepatas.service.CuestionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/cuestionario")
public class CuestionarioController {

    @Autowired
    CuestionarioService cuestionarioService;

    @PostMapping("/create")
    public List<CuestionarioEntity> create(@RequestBody List<CuestionarioEntity> lista) {
        return cuestionarioService.create(lista);
    }
}
