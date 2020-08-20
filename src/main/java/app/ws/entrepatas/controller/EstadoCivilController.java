package app.ws.entrepatas.controller;

import app.ws.entrepatas.model.EstadoCivilEntity;
import app.ws.entrepatas.service.EstadoCivilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("api/estadoCivil")
@CrossOrigin("*")
public class EstadoCivilController {

    @Autowired
    EstadoCivilService estadoCivilService;

    @GetMapping("/findAll")
    public List<EstadoCivilEntity> findAll() {
        List<EstadoCivilEntity> list = estadoCivilService.findAll();
        return list;
    }
}
