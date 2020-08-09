package app.ws.entrepatas.controller;


import app.ws.entrepatas.model.CondicionEntity;
import app.ws.entrepatas.service.CondicionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/condicion")
public class CondicionController {

    @Autowired
    CondicionService condicionService;

    @GetMapping("findAll")
    public List<CondicionEntity> findAll(){
        return condicionService.findAll();
    }
}
