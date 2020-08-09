package app.ws.entrepatas.controller;

import app.ws.entrepatas.model.EventoEntity;
import app.ws.entrepatas.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/evento")
public class EventoController {

    @Autowired
    EventoService eventoService;

    @PostMapping("/create")
    public EventoEntity create(@RequestBody EventoEntity evento) {
        return eventoService.create(evento);
    }

    @GetMapping("findAll")
    public List<EventoEntity> findAll(){
        return eventoService.findAll();
    }
}
