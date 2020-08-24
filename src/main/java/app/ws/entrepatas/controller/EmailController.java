package app.ws.entrepatas.controller;

import app.ws.entrepatas.model.PersonaEntity;
import app.ws.entrepatas.model.UsuarioEntity;
import app.ws.entrepatas.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/email")
@CrossOrigin("*")
public class EmailController {


    @Autowired
    EmailService emailService;

    @PostMapping("/send")
    public void send() {
        UsuarioEntity user = new UsuarioEntity();
        PersonaEntity persona = new PersonaEntity();

        persona.setNombreCompleto("LUIS PASTOR");
        persona.setCorreo("jolteon_10@hotmail.com");
        persona.setNumeroDocumento("7878787");
        user.setUuid("4206110e-ce0b-41f4-8c31-9f9adfeb4af1");
        user.setPersona(persona);
        user.setUsername("68676866");
        emailService.sendEmailActiveAccount(user);

    }
}
